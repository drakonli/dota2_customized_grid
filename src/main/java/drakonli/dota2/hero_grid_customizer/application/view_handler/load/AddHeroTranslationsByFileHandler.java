package drakonli.dota2.hero_grid_customizer.application.view_handler.load;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationsToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.importer.HeroNamesByFileImporter;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.File;
import java.util.List;

public class AddHeroTranslationsByFileHandler implements LoadButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final HeroNamesByFileImporter importer;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public AddHeroTranslationsByFileHandler(
            HeroGridViewModel heroGridViewModel,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            HeroNamesByFileImporter importer,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.importer = importer;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslationsViewModels());

            File file = this.exportImportHeroGridByFileViewModel
                    .getOptionalChosenHeroGridFile()
                    .orElseThrow(() -> new NullPointerException("No File was chosen"));

            this.importer.importHeroNamesByFile(file, heroTranslations);

            this.heroTranslationsToViewModelMapper.map(heroTranslations, this.heroGridViewModel.getHeroTranslationsViewModels());
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
