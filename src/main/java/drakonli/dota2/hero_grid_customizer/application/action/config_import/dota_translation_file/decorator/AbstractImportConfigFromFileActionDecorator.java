package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.decorator;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.IImportConfigFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AbstractImportConfigFromFileActionDecorator implements IImportConfigFromFileAction
{
    private final IImportConfigFromFileAction importConfigFromFileAction;

    public AbstractImportConfigFromFileActionDecorator(
            IImportConfigFromFileAction importConfigFromFileAction
    )
    {
        this.importConfigFromFileAction = importConfigFromFileAction;
    }

    @Override
    public void importConfig(
            File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto
    ) throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        this.importConfigFromFileAction.importConfig(file, heroNameCustomizationVMListToImportInto);
    }
}
