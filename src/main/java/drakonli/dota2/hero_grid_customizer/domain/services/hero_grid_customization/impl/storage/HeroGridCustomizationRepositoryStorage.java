package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.storage;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.repositories.IHeroGridCustomizationRepository;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;

import java.util.List;
import java.util.Optional;

public class HeroGridCustomizationRepositoryStorage implements IHeroGridCustomizationStorage
{
    private IHeroGridCustomizationRepository repository;

    public HeroGridCustomizationRepositoryStorage(IHeroGridCustomizationRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void store(HeroGridCustomization heroGridCustomization)
    {
        List<HeroGridCustomization> customizations = this.repository.findByName(heroGridCustomization.getName());

        if (!customizations.isEmpty()) {
            this.repository.deleteList(customizations);
        }

        this.repository.save(heroGridCustomization);
    }

    @Override
    public Optional<HeroGridCustomization> getLatest()
    {
        return this.repository.findLatest();
    }
}
