package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.from_file.decorator;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.from_file.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridCustomizationByFileVM;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SetCurrentFileDecorator extends AbstractImportHeroGridCustomizationFromFileActionDecorator
{
    private final ExportImportHeroGridCustomizationByFileVM exportImportHeroGridCustomizationByFileVM;

    public SetCurrentFileDecorator(
            IImportHeroGridCustomizationFromFileAction importConfigFromFileAction,
            ExportImportHeroGridCustomizationByFileVM exportImportHeroGridCustomizationByFileVM
    )
    {
        super(importConfigFromFileAction);

        this.exportImportHeroGridCustomizationByFileVM = exportImportHeroGridCustomizationByFileVM;
    }

    @Override
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        super.importCustomization(file, heroNameCustomizationVMListToImportInto);

        this.exportImportHeroGridCustomizationByFileVM.setChosenHeroGridFile(file);
    }
}