package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.import_customization.from_file.decorator;

import drakonli.dota2.hero_grid_customizer.application.actions.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AbstractImportHeroGridCustomizationFromFileActionDecorator
        implements IImportHeroGridCustomizationFromFileAction
{
    private final IImportHeroGridCustomizationFromFileAction importHeroGridCustomizationFromFileAction;

    public AbstractImportHeroGridCustomizationFromFileActionDecorator(
            IImportHeroGridCustomizationFromFileAction importHeroGridCustomizationFromFileAction
    )
    {
        this.importHeroGridCustomizationFromFileAction = importHeroGridCustomizationFromFileAction;
    }

    @Override
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        this.importHeroGridCustomizationFromFileAction.importCustomization(
                file,
                heroNameCustomizationVMListToImportInto
        );
    }
}
