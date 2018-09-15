package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

import java.util.Optional;

public interface IHeroGridCustomizationStorage
{
    void store(HeroGridCustomization heroGridCustomization) throws StorageException;

    Optional<HeroGridCustomization> getLatest() throws StorageException;
}
