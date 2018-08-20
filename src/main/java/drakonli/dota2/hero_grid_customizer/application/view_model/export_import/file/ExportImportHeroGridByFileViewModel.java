package drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file;

import javafx.beans.property.SimpleBooleanProperty;

import java.io.File;
import java.util.Optional;

public class ExportImportHeroGridByFileViewModel
{
    private File chosenHeroGridFile;
    private SimpleBooleanProperty chosenHeroGridFileAvailableProperty = new SimpleBooleanProperty(false);

    public Optional<File> getOptionalChosenHeroGridFile()
    {
        return Optional.ofNullable(this.chosenHeroGridFile);
    }

    public void setChosenHeroGridFile(File chosenHeroGridFile)
    {
        this.chosenHeroGridFile = chosenHeroGridFile;
        this.chosenHeroGridFileAvailableProperty.setValue(null != chosenHeroGridFile);
    }

    public SimpleBooleanProperty getChosenHeroGridFileAvailableProperty()
    {
        return this.chosenHeroGridFileAvailableProperty;
    }
}
