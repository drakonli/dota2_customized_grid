package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IExportConfigIntoFileAction
{
    public void exportConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToExport) throws
            InvalidFileFormatException, IOException, ApplicationActionException;
}
