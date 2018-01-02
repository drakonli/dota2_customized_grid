package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityConverter;

import java.io.IOException;

public class StoreHeroNamesHandler implements SaveButtonHandlerInterface
{
    private final HeroNamesByFileStorage heroNamesStorage;
    private final HeroTranslationViewModelsToEntityConverter converter;

    public StoreHeroNamesHandler(
            HeroNamesByFileStorage heroNamesStorage,
            HeroTranslationViewModelsToEntityConverter converter
    )
    {
        this.heroNamesStorage = heroNamesStorage;
        this.converter = converter;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            this.heroNamesStorage.store(
                    converter.convert(heroGridViewModel.getHeroTranslations())
            );
        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
