package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file.ExportImportHeroGridByFileViewModel;
import drakonli.jcomponents.file.backuper.FileBackuper;

import java.io.IOException;

public class BackupHeroTranslationFileHandler implements SaveButtonHandlerInterface
{
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final FileBackuper backuper;

    public BackupHeroTranslationFileHandler(
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            FileBackuper backuper
    )
    {
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.backuper = backuper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            this.backuper.backupOriginal(
                    this.exportImportHeroGridByFileViewModel.getChosenHeroGridFile()
            );

        } catch (IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
