package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.publisher.IExportConfigIntoFileActionEventPublisher;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicExportConfigIntoFileAction implements IExportConfigIntoFileAction
{
    private final IHeroGridConfigToFileExporter exporter;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;
    private final IExportConfigIntoFileActionEventPublisher eventPublisher;

    public BasicExportConfigIntoFileAction(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper,
            IExportConfigIntoFileActionEventPublisher eventPublisher
    )
    {
        this.exporter = heroGridConfigToFileExporter;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void exportConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToExport)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        List<HeroTranslation> heroTranslationsToExport = this.heroTranslationViewModelsToDomainModelMapper
                .mapToNewEntityList(heroTranslationViewModelsToExport);

        this.eventPublisher.publishBeforeExportEvent(file, heroTranslationViewModelsToExport, heroTranslationsToExport);

        try {
            this.exporter.export(file, heroTranslationsToExport);
        } catch (ExportException e) {
            throw new ApplicationActionException(e);
        }

        this.eventPublisher.publishAfterExportEvent(file, heroTranslationViewModelsToExport, heroTranslationsToExport);
    }
}
