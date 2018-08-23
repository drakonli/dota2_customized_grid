package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroGridCustomizationToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicImportConfigFromFileAction implements IImportConfigFromFileAction
{
    private final IHeroGridConfigFromFileImporter importer;
    private final HeroGridCustomizationToViewModelMapper heroGridCustomizationToViewModelMapper;

    public BasicImportConfigFromFileAction(
            IHeroGridConfigFromFileImporter importer,
            HeroGridCustomizationToViewModelMapper heroGridCustomizationToViewModelMapper
    )
    {
        this.importer = importer;
        this.heroGridCustomizationToViewModelMapper = heroGridCustomizationToViewModelMapper;
    }

    @Override
    public void importConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto) throws
            InvalidFileFormatException, IOException
    {
        HeroGridCustomization heroGridCustomization = this.importer.importHeroNamesByFile(file);

        this.heroGridCustomizationToViewModelMapper
                .map(heroGridCustomization, heroTranslationViewModelsToImportInto);
    }
}
