package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroNamesGridCustomizationToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicImportConfigFromFileAction implements IImportConfigFromFileAction
{
    private final IHeroGridConfigFromFileImporter importer;
    private final HeroNamesGridCustomizationToViewModelMapper heroNamesGridCustomizationToViewModelMapper;

    public BasicImportConfigFromFileAction(
            IHeroGridConfigFromFileImporter importer,
            HeroNamesGridCustomizationToViewModelMapper heroNamesGridCustomizationToViewModelMapper
    )
    {
        this.importer = importer;
        this.heroNamesGridCustomizationToViewModelMapper = heroNamesGridCustomizationToViewModelMapper;
    }

    @Override
    public void importConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto) throws
            InvalidFileFormatException, IOException
    {
        HeroNamesGridCustomization heroNamesGridCustomization = this.importer.importHeroNamesByFile(file);

        this.heroNamesGridCustomizationToViewModelMapper
                .map(heroNamesGridCustomization, heroTranslationViewModelsToImportInto);
    }
}
