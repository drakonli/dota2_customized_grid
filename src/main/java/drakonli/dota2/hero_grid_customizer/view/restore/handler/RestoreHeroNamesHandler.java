package drakonli.dota2.hero_grid_customizer.view.restore.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.restorer.HeroNamesByFileStorageRestorer;
import drakonli.dota2.hero_grid_customizer.component.hero.names.restorer.LastVersionOfHeroNamesIsEmptyException;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationsToViewModelMapper;

import java.io.IOException;
import java.util.List;

public class RestoreHeroNamesHandler implements RestoreButtonHandlerInterface
{
    private final HeroNamesByFileStorageRestorer restorer;
    private final HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public RestoreHeroNamesHandler(
            HeroNamesByFileStorageRestorer restorer,
            HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.restorer = restorer;
        this.heroTranslationViewModelsToEntityMapper = heroTranslationViewModelsToEntityMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToEntityMapper
                    .mapToNewEntityList(heroGridViewModel.getHeroTranslations());

            this.restorer.restoreLatestHeroNames(heroTranslations);

            this.heroTranslationsToViewModelMapper.map(heroTranslations, heroGridViewModel.getHeroTranslations());
        } catch (IOException | ClassNotFoundException | LastVersionOfHeroNamesIsEmptyException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
