package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.publisher.IExportConfigIntoFileActionEventPublisher;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroGridCustomizationByViewModelsFactory;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicExportConfigIntoFileAction implements IExportConfigIntoFileAction
{
    private final IHeroGridConfigToFileExporter exporter;
    private final HeroGridCustomizationByViewModelsFactory heroGridCustomizationFactory;
    private final IExportConfigIntoFileActionEventPublisher eventPublisher;

    public BasicExportConfigIntoFileAction(
            IHeroGridConfigToFileExporter exporter,
            HeroGridCustomizationByViewModelsFactory heroGridCustomizationFactory,
            IExportConfigIntoFileActionEventPublisher eventPublisher
    )
    {
        this.exporter = exporter;
        this.heroGridCustomizationFactory = heroGridCustomizationFactory;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void exportConfig(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToExport)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        HeroGridCustomization heroGridCustomization =
                this.heroGridCustomizationFactory.create(heroNameCustomizationVMListToExport);

        this.eventPublisher.publishBeforeExportEvent(
                file,
                heroNameCustomizationVMListToExport,
                heroGridCustomization
        );

        try {
            this.exporter.export(file, heroGridCustomization);
        } catch (ExportException e) {
            throw new ApplicationActionException(e);
        }

        this.eventPublisher.publishAfterExportEvent(
                file,
                heroNameCustomizationVMListToExport,
                heroGridCustomization
        );
    }
}
