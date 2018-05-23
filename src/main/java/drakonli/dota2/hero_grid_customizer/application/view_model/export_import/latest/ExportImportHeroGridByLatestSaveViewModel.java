package drakonli.dota2.hero_grid_customizer.application.view_model.export_import.latest;

import javafx.beans.property.SimpleBooleanProperty;

public class ExportImportHeroGridByLatestSaveViewModel
{
    private final SimpleBooleanProperty isRestoreAvailable;

    public ExportImportHeroGridByLatestSaveViewModel(SimpleBooleanProperty isRestoreAvailable)
    {
        this.isRestoreAvailable = isRestoreAvailable;
    }

    public boolean isIsRestoreAvailable()
    {
        return isRestoreAvailable.get();
    }

    public void setIsRestoreAvailable(boolean isRestoreAvailable)
    {
        this.isRestoreAvailable.set(isRestoreAvailable);
    }

    public SimpleBooleanProperty getRestoreAvailableProperty()
    {
        return this.isRestoreAvailable;
    }
}
