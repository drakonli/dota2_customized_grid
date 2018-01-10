package drakonli.dota2.hero_grid_customizer.view.load.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer.HeroNamesByFileImporter;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;

import java.io.IOException;

public class AddHeroTranslationsByFileHandler implements LoadButtonHandlerInterface
{
    private final HeroNamesByFileImporter importer;

    public AddHeroTranslationsByFileHandler(HeroNamesByFileImporter importer)
    {
        this.importer = importer;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            this.importer.importHeroNamesByFile(
                    heroGridViewModel.getChosenHeroGridFile(),
                    heroGridViewModel.getHeroTranslations()
            );
        } catch (IOException | Dota2InvalidFileFormatException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
