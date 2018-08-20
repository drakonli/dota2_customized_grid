package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.publisher.IExportConfigIntoFileActionEventPublisher;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicExportConfigIntoFileAction implements IExportConfigIntoFileAction
{
    private final IHeroGridConfigToFileExporter heroGridConfigToFileExporter;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;
    private final IExportConfigIntoFileActionEventPublisher eventPublisher;

    public BasicExportConfigIntoFileAction(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper,
            IExportConfigIntoFileActionEventPublisher eventPublisher
    )
    {
        this.heroGridConfigToFileExporter = heroGridConfigToFileExporter;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void exportConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModels)
            throws InvalidFileFormatException, IOException
    {
        List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                .mapToNewEntityList(heroTranslationViewModels);

        this.eventPublisher.publishBeforeExportEvent(file, heroTranslationViewModels , heroTranslations);

        this.heroGridConfigToFileExporter.export(file, heroTranslations);
    }
}
