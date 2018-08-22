package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class FileStorageRestoreAvailabilityManager implements RestoreAvailabilityManagerInterface
{
    private final HeroNamesByFileStorage heroNamesByFileStorage;

    public FileStorageRestoreAvailabilityManager(
            HeroNamesByFileStorage heroNamesByFileStorage
    )
    {
        this.heroNamesByFileStorage = heroNamesByFileStorage;
    }

    @Override
    public Boolean isRestoreAvailable()
    {
        try {
            List<HeroNameCustomization> latestHeroGridSave = this.heroNamesByFileStorage.getLatest();

            return !latestHeroGridSave.isEmpty();
        } catch (StorageException e) {
            return false;
        }
    }
}
