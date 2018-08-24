package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.latest_save;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.HeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.StorageException;

import java.util.List;

public class ImportHeroGridCustomizationFromStorageByLatestSaveAction implements
        IImportHeroGridCustomizationByLatestSaveAction
{
    private final HeroGridCustomizationByFileStorage storage;
    private final HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper;

    public ImportHeroGridCustomizationFromStorageByLatestSaveAction(
            HeroGridCustomizationByFileStorage storage,
            HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper
    )
    {
        this.storage = storage;
        this.heroGridCustomizationToViewModelsMapper = heroGridCustomizationToViewModelsMapper;
    }

    @Override
    public void importCustomization(List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException
    {
        HeroGridCustomization heroGridCustomization = this.storage.getLatest();

        if (heroGridCustomization.isEmpty()) {
            throw new LastVersionOfHeroGridCustomizationIsEmptyException();
        }

        this.heroGridCustomizationToViewModelsMapper
                .map(heroGridCustomization, heroNameCustomizationVMListToImportInto);
    }
}
