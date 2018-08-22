package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;

public class FileStorageRestoreAvailabilityManager implements RestoreAvailabilityManagerInterface
{
    private final HeroNamesGridCustomizationByFileStorage heroNamesGridCustomizationByFileStorage;

    public FileStorageRestoreAvailabilityManager(
            HeroNamesGridCustomizationByFileStorage heroNamesGridCustomizationByFileStorage
    )
    {
        this.heroNamesGridCustomizationByFileStorage = heroNamesGridCustomizationByFileStorage;
    }

    @Override
    public Boolean isRestoreAvailable()
    {
        try {
            HeroNamesGridCustomization latestStoredHeroGridCustomization
                    = this.heroNamesGridCustomizationByFileStorage.getLatest();

            return !latestStoredHeroGridCustomization.isEmpty();
        } catch (StorageException e) {
            return false;
        }
    }
}
