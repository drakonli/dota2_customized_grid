package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.from_file.decorator;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.from_file.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AbstractImportHeroGridCustomizationFromFileActionDecorator
        implements IImportHeroGridCustomizationFromFileAction
{
    private final IImportHeroGridCustomizationFromFileAction importConfigFromFileAction;

    public AbstractImportHeroGridCustomizationFromFileActionDecorator(
            IImportHeroGridCustomizationFromFileAction importConfigFromFileAction
    )
    {
        this.importConfigFromFileAction = importConfigFromFileAction;
    }

    @Override
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        this.importConfigFromFileAction.importCustomization(file, heroNameCustomizationVMListToImportInto);
    }
}
