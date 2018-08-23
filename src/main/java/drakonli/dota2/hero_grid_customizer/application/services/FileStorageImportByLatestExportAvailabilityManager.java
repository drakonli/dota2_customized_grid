package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

public class FileStorageImportByLatestExportAvailabilityManager implements ImportByLatestExportAvailabilityManagerInterface
{
    private final HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage;

    public FileStorageImportByLatestExportAvailabilityManager(
            HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage
    )
    {
        this.heroGridCustomizationByFileStorage = heroGridCustomizationByFileStorage;
    }

    @Override
    public Boolean isImportByLatestExportAvailable()
    {
        try {
            HeroGridCustomization latestStoredHeroGridCustomization
                    = this.heroGridCustomizationByFileStorage.getLatest();

            return !latestStoredHeroGridCustomization.isEmpty();
        } catch (StorageException e) {
            return false;
        }
    }
}
