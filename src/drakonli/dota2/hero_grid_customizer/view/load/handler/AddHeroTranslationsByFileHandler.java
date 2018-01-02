package drakonli.dota2.hero_grid_customizer.view.load.handler;

import drakonli.component.file.chooser.InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer.HeroNamesFileImporter;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;

import java.io.FileNotFoundException;

public class AddHeroTranslationsByFileHandler implements LoadButtonHandlerInterface
{
    private final HeroNamesFileImporter importer;

    public AddHeroTranslationsByFileHandler(HeroNamesFileImporter importer)
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
        } catch (FileNotFoundException | InvalidFileFormatException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
