package drakonli.dota2.hero_grid_customizer.application.view_handler.restore;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationsToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.restorer.HeroNamesByFileStorageRestorer;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.restorer.LastVersionOfHeroNamesIsEmptyException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.IOException;
import java.util.List;

public class RestoreHeroNamesHandler implements RestoreButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesByFileStorageRestorer restorer;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public RestoreHeroNamesHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesByFileStorageRestorer restorer,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.restorer = restorer;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslationsViewModels());

            this.restorer.restoreLatestHeroNames(heroTranslations);

            this.heroTranslationsToViewModelMapper.map(heroTranslations, this.heroGridViewModel.getHeroTranslationsViewModels());
        } catch (IOException | ClassNotFoundException | LastVersionOfHeroNamesIsEmptyException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
