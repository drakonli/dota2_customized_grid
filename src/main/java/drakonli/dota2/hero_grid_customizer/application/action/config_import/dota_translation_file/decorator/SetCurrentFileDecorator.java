package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.decorator;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.IImportConfigFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SetCurrentFileDecorator extends AbstractImportConfigFromFileActionDecorator
{
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;

    public SetCurrentFileDecorator(
            IImportConfigFromFileAction importConfigFromFileAction,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel
    )
    {
        super(importConfigFromFileAction);

        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
    }

    @Override
    public void importConfig(
            File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto
    ) throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        super.importConfig(file, heroNameCustomizationVMListToImportInto);

        this.exportImportHeroGridByFileViewModel.setChosenHeroGridFile(file);
    }
}
