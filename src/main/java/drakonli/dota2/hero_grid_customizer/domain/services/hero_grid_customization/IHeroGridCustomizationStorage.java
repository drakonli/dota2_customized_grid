package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

public interface IHeroGridCustomizationStorage
{
    void store(HeroGridCustomization heroGridCustomization) throws StorageException;

    HeroGridCustomization getLatest() throws StorageException;
}
