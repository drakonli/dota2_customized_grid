package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicImportHeroGridCustomizationFromFileAction implements IImportHeroGridCustomizationFromFileAction
{
    private final IHeroGridConfigFromFileImporter importer;
    private final HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper;

    public BasicImportHeroGridCustomizationFromFileAction(
            IHeroGridConfigFromFileImporter importer,
            HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper
    )
    {
        this.importer = importer;
        this.heroGridCustomizationToViewModelsMapper = heroGridCustomizationToViewModelsMapper;
    }

    @Override
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException
    {
        HeroGridCustomization heroGridCustomization = this.importer.importHeroNamesByFile(file);

        this.heroGridCustomizationToViewModelsMapper
                .map(heroGridCustomization, heroNameCustomizationVMListToImportInto);
    }
}
