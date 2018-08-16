package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.IOException;
import java.util.List;

public class ReplaceHeroNamesInTranslationsFileHandler implements SaveButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final IHeroGridConfigToFileExporter heroGridConfigToFileExporter;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;

    public ReplaceHeroNamesInTranslationsFileHandler(
            HeroGridViewModel heroGridViewModel,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.heroGridConfigToFileExporter = heroGridConfigToFileExporter;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslations());

            this.heroGridConfigToFileExporter.export(
                    this.exportImportHeroGridByFileViewModel.getChosenHeroGridFile(),
                    heroTranslations
            );
        } catch (InvalidFileFormatException | IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
