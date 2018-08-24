package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.StorageException;

public class FileStorageImportByLatestExportAvailabilityManager implements ImportByLatestExportAvailabilityManagerInterface
{
    private final IHeroGridCustomizationStorage heroGridCustomizationStorage;

    public FileStorageImportByLatestExportAvailabilityManager(
            IHeroGridCustomizationStorage heroGridCustomizationStorage
    )
    {
        this.heroGridCustomizationStorage = heroGridCustomizationStorage;
    }

    @Override
    public Boolean isImportByLatestExportAvailable()
    {
        try {
            HeroGridCustomization latestStoredHeroGridCustomization
                    = this.heroGridCustomizationStorage.getLatest();

            return !latestStoredHeroGridCustomization.isEmpty();
        } catch (StorageException e) {
            return false;
        }
    }
}
