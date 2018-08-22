package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.predicate;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.function.Predicate;

public class HeroTranslationByHeroCodePredicate implements Predicate<HeroNameCustomization>
{
    private final String heroCode;

    public HeroTranslationByHeroCodePredicate(String heroCode)
    {
        this.heroCode = heroCode;
    }

    @Override
    public boolean test(HeroNameCustomization heroNameCustomization)
    {
        return heroNameCustomization.getHeroCode().equals(this.heroCode);
    }
}
