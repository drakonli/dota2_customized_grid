package drakonli.dota2.hero_grid_customizer.view.restore.handler;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.restorer.HeroNamesByFileStorageRestorer;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.restorer.LastVersionOfHeroNamesIsEmptyException;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationsToViewModelMapper;

import java.io.IOException;
import java.util.List;

public class RestoreHeroNamesHandler implements RestoreButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesByFileStorageRestorer restorer;
    private final HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public RestoreHeroNamesHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesByFileStorageRestorer restorer,
            HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.restorer = restorer;
        this.heroTranslationViewModelsToEntityMapper = heroTranslationViewModelsToEntityMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToEntityMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslations());

            this.restorer.restoreLatestHeroNames(heroTranslations);

            this.heroTranslationsToViewModelMapper.map(heroTranslations, this.heroGridViewModel.getHeroTranslations());
        } catch (IOException | ClassNotFoundException | LastVersionOfHeroNamesIsEmptyException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
