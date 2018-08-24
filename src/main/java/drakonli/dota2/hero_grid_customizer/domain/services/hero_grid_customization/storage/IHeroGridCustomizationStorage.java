package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

public interface IHeroGridCustomizationStorage
{
    void store(HeroGridCustomization heroGridCustomization) throws StorageException;

    HeroGridCustomization getLatest() throws StorageException;
}
