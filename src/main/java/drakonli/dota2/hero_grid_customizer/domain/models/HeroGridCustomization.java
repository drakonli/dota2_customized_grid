package drakonli.dota2.hero_grid_customizer.domain.models;

import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl.HeroNameCustomizationSameAsHeroUIDPredicate;

import java.util.ArrayList;
import java.util.Optional;

public class HeroGridCustomization extends ArrayList<HeroNameCustomization>
{
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
