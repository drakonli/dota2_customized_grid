package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.function.Predicate;

public class HeroTranslationByHeroNameUIDPredicate implements Predicate<HeroNameCustomization>
{
    private final String heroCode;

    public HeroTranslationByHeroNameUIDPredicate(String heroCode)
    {
        this.heroCode = heroCode;
    }

    @Override
    public boolean test(HeroNameCustomization heroNameCustomization)
    {
        return heroNameCustomization.getHeroNameUID().equals(this.heroCode);
    }
}
