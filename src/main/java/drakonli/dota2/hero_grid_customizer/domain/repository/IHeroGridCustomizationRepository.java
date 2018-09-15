package drakonli.dota2.hero_grid_customizer.domain.repository;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

import java.util.List;
import java.util.Optional;

public interface IHeroGridCustomizationRepository
{
    public List<HeroGridCustomization> findByName(String name);

    public void deleteList(List<HeroGridCustomization> customizations);

    public void save(HeroGridCustomization customization);

    public Optional<HeroGridCustomization> findLatest();
}
