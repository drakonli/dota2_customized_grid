package drakonli.dota2.hero_grid_customizer.domain.models;

import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl.HeroNameCustomizationSameAsHeroUIDPredicate;

import java.util.ArrayList;
import java.util.Optional;

public class HeroGridCustomization extends ArrayList<HeroNameCustomization>
{
    private String name;

    public HeroGridCustomization(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Optional<HeroNameCustomization> findHeroNameCustomizationByHeroNameUUID(String heroNameUUID)
    {
        return this
                .stream()
                .filter(
                        new HeroNameCustomizationSameAsHeroUIDPredicate(heroNameUUID)
                )
                .findFirst();
    }
}
