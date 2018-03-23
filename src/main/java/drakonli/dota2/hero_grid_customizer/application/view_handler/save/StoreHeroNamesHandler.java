package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.IOException;
import java.util.List;

public class StoreHeroNamesHandler implements SaveButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesByFileStorage heroNamesStorage;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;

    public StoreHeroNamesHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesByFileStorage heroNamesStorage,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.heroNamesStorage = heroNamesStorage;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslations());

            this.heroNamesStorage.store(heroTranslations);
        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}