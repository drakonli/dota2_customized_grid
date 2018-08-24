package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization;

import drakonli.dota2.hero_grid_customizer.application.actions.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IExportHeroGridCustomizationIntoFileAction
{
    public void exportCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToExport)
            throws InvalidFileFormatException, IOException, ApplicationActionException;
}
