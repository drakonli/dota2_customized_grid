package drakonli.dota2.hero_grid_customizer.application.services.impl;

import drakonli.dota2.hero_grid_customizer.application.services.IImportByLatestExportAvailabilityManager;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;

import java.util.Optional;

public class FileStorageImportByLatestExportAvailabilityManager implements IImportByLatestExportAvailabilityManager
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
            Optional<HeroGridCustomization> latestStoredHeroGridCustomization
                    = this.heroGridCustomizationStorage.getLatest();

            return latestStoredHeroGridCustomization.isPresent();
        } catch (StorageException e) {
            return false;
        }
    }
}
