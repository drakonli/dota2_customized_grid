package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.import_customization.from_file;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.IHeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportHeroGridCustomizationFromFileAction implements IImportHeroGridCustomizationFromFileAction
{
    private final IHeroGridCustomizationFromFileImporter   importer;
    private final IHeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper;

    public ImportHeroGridCustomizationFromFileAction(
            IHeroGridCustomizationFromFileImporter importer,
            IHeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper
    )
    {
        this.importer = importer;
        this.heroGridCustomizationToViewModelsMapper = heroGridCustomizationToViewModelsMapper;
    }

    @Override
    public void importCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws InvalidFileFormatException, IOException
    {
        HeroGridCustomization heroGridCustomization = this.importer.importCustomization(file);

        this.heroGridCustomizationToViewModelsMapper
                .map(heroGridCustomization, heroNameCustomizationVMListToImportInto);
    }
}
