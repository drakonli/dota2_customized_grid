package drakonli.dota2.hero_grid_customizer.view.load.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer.HeroNamesByFileImporter;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationsToViewModelMapper;

import java.io.IOException;
import java.util.List;

public class AddHeroTranslationsByFileHandler implements LoadButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesByFileImporter importer;
    private final HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public AddHeroTranslationsByFileHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesByFileImporter importer,
            HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.importer = importer;
        this.heroTranslationViewModelsToEntityMapper = heroTranslationViewModelsToEntityMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToEntityMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslations());

            this.importer.importHeroNamesByFile(
                    this.heroGridViewModel.getChosenHeroGridFile(),
                    heroTranslations
            );

            this.heroTranslationsToViewModelMapper.map(heroTranslations, this.heroGridViewModel.getHeroTranslations());
        } catch (IOException | Dota2InvalidFileFormatException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
