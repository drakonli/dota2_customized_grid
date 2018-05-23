package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.IOException;
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
            List<HeroTranslation> latestHeroGridSave = this.heroNamesByFileStorage.getLatest();

            return !latestHeroGridSave.isEmpty();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }
}
