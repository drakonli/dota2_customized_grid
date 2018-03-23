package drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file;

import java.io.File;

public class ExportImportHeroGridByFileViewModel
{
    private File chosenHeroGridFile;

    public File getChosenHeroGridFile()
    {
        return this.chosenHeroGridFile;
    }

    public void setChosenHeroGridFile(File chosenHeroGridFile)
    {
        this.chosenHeroGridFile = chosenHeroGridFile;
    }
}
