package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;

import java.io.IOException;
import java.util.List;

public class StoreHeroNamesHandler implements SaveButtonHandlerInterface
{
    private final HeroNamesByFileStorage heroNamesStorage;
    private final HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;

    public StoreHeroNamesHandler(
            HeroNamesByFileStorage heroNamesStorage,
            HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper
    )
    {
        this.heroNamesStorage = heroNamesStorage;
        this.heroTranslationViewModelsToEntityMapper = heroTranslationViewModelsToEntityMapper;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToEntityMapper
                    .mapToNewEntityList(heroGridViewModel.getHeroTranslations());

            this.heroNamesStorage.store(heroTranslations);
        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
