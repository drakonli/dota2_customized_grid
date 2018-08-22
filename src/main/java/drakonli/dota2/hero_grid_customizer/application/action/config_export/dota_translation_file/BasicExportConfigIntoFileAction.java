package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.publisher.IExportConfigIntoFileActionEventPublisher;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroNamesGridCustomizationByViewModelFactory;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicExportConfigIntoFileAction implements IExportConfigIntoFileAction
{
    private final IHeroGridConfigToFileExporter exporter;
    private final HeroNamesGridCustomizationByViewModelFactory heroNamesGridCustomizationFactory;
    private final IExportConfigIntoFileActionEventPublisher eventPublisher;

    public BasicExportConfigIntoFileAction(
            IHeroGridConfigToFileExporter exporter,
            HeroNamesGridCustomizationByViewModelFactory heroNamesGridCustomizationFactory,
            IExportConfigIntoFileActionEventPublisher eventPublisher
    )
    {
        this.exporter = exporter;
        this.heroNamesGridCustomizationFactory = heroNamesGridCustomizationFactory;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void exportConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToExport)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        HeroNamesGridCustomization heroNamesGridCustomization =
                this.heroNamesGridCustomizationFactory.create(heroTranslationViewModelsToExport);

        this.eventPublisher.publishBeforeExportEvent(
                file,
                heroTranslationViewModelsToExport,
                heroNamesGridCustomization
        );

        try {
            this.exporter.export(file, heroNamesGridCustomization);
        } catch (ExportException e) {
            throw new ApplicationActionException(e);
        }

        this.eventPublisher.publishAfterExportEvent(
                file,
                heroTranslationViewModelsToExport,
                heroNamesGridCustomization
        );
    }
}
