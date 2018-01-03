package drakonli.dota2.hero_grid_customizer.view.restore.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.restorer.HeroNamesByFileStorageRestorer;
import drakonli.dota2.hero_grid_customizer.component.hero.names.restorer.LastVersionOfHeroNamesIsEmptyException;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;

import java.io.IOException;

public class RestoreHeroNamesHandler implements RestoreButtonHandlerInterface
{
    private final HeroNamesByFileStorageRestorer restorer;

    public RestoreHeroNamesHandler(HeroNamesByFileStorageRestorer restorer)
    {
        this.restorer = restorer;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            this.restorer.restoreLatestHeroNames(heroGridViewModel.getHeroTranslations());
        } catch (IOException | ClassNotFoundException | LastVersionOfHeroNamesIsEmptyException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
