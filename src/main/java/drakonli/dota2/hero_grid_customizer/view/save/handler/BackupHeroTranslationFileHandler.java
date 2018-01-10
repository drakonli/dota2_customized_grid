package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.jcomponents.file.backuper.FileBackuper;

import java.io.IOException;

public class BackupHeroTranslationFileHandler implements SaveButtonHandlerInterface
{
    private final FileBackuper backuper;

    public BackupHeroTranslationFileHandler(FileBackuper backuper)
    {
        this.backuper = backuper;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            this.backuper.backupOriginal(
                    heroGridViewModel.getChosenHeroGridFile()
            );
        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
