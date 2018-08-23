package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

public class FileStorageRestoreAvailabilityManager implements RestoreAvailabilityManagerInterface
{
    private final HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage;

    public FileStorageRestoreAvailabilityManager(
            HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage
    )
    {
        this.heroGridCustomizationByFileStorage = heroGridCustomizationByFileStorage;
    }

    @Override
    public Boolean isRestoreAvailable()
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
