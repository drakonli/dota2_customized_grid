package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IExportConfigIntoFileAction
{
    public void exportConfig(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToExport) throws
            InvalidFileFormatException, IOException, ApplicationActionException;
}
