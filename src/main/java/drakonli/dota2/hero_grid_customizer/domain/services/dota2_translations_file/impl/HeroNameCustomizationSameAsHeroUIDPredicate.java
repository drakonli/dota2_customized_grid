package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;

import java.util.function.Predicate;

public class HeroNameCustomizationSameAsHeroUIDPredicate implements Predicate<HeroNameCustomization>
{
    private final String heroCode;

    public HeroNameCustomizationSameAsHeroUIDPredicate(String heroCode)
    {
        this.heroCode = heroCode;
    }

    @Override
    public boolean test(HeroNameCustomization heroNameCustomization)
    {
        return heroNameCustomization.getHeroNameUID().equals(this.heroCode);
    }
}
