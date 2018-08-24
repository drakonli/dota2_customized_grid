package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.import_customization.latest_save;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IImportHeroGridCustomizationByLatestSaveAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.IHeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;

import java.util.List;

public class ImportHeroGridCustomizationFromStorageByLatestSaveAction implements
        IImportHeroGridCustomizationByLatestSaveAction
{
    private final IHeroGridCustomizationStorage            storage;
    private final IHeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper;

    public ImportHeroGridCustomizationFromStorageByLatestSaveAction(
            IHeroGridCustomizationStorage storage,
            IHeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper
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
