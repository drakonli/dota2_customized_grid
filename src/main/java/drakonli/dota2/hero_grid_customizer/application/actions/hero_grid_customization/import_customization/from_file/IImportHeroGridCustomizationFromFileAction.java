package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.import_customization.from_file;

import drakonli.dota2.hero_grid_customizer.application.actions.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IImportHeroGridCustomizationFromFileAction
{
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException, ApplicationActionException;
}
