package drakonli.dota2.hero_grid_customizer.domain.models;

import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl.HeroNameCustomizationSameAsHeroUIDPredicate;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeroGridCustomization implements Serializable
{
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HeroNameCustomization> heroNameCustomizations = new ArrayList<>();

    public HeroGridCustomization(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public List<HeroNameCustomization> getHeroNameCustomizations()
    {
        return heroNameCustomizations;
    }

    public Optional<HeroNameCustomization> findHeroNameCustomizationByHeroNameUUID(String heroNameUUID)
    {
        return this.heroNameCustomizations
                .stream()
                .filter(
                        new HeroNameCustomizationSameAsHeroUIDPredicate(heroNameUUID)
                )
                .findFirst();
    }
}
