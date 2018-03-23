package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.jcomponents.file.backuper.FileBackuper;

import java.io.IOException;

public class BackupHeroTranslationFileHandler implements SaveButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final FileBackuper backuper;

    public BackupHeroTranslationFileHandler(
            HeroGridViewModel heroGridViewModel,
            FileBackuper backuper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.backuper = backuper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            this.backuper.backupOriginal(
                    this.heroGridViewModel.getChosenHeroGridFile()
            );
        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
