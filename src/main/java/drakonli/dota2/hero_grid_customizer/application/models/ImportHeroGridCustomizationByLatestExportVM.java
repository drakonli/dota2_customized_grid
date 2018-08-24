package drakonli.dota2.hero_grid_customizer.application.models;

import javafx.beans.property.SimpleBooleanProperty;

public class ImportHeroGridCustomizationByLatestExportVM
{
    private final SimpleBooleanProperty isImportByLatestExportAvailable;

    public ImportHeroGridCustomizationByLatestExportVM(SimpleBooleanProperty isImportByLatestExportAvailable)
    {
        this.isImportByLatestExportAvailable = isImportByLatestExportAvailable;
    }

    public void setIsImportByLatestExportAvailable(boolean isImportByLatestExportAvailable)
    {
        this.isImportByLatestExportAvailable.set(isImportByLatestExportAvailable);
    }

    public SimpleBooleanProperty getImportByLatestExportAvailableProperty()
    {
        return this.isImportByLatestExportAvailable;
    }
}
