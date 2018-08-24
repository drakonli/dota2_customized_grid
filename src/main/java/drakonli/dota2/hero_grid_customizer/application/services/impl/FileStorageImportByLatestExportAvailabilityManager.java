package drakonli.dota2.hero_grid_customizer.application.services.impl;

import drakonli.dota2.hero_grid_customizer.application.services.ImportByLatestExportAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;

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