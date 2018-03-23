package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.predicate;

import drakonli.dota2.hero_grid_customizer.domain.entity.HeroTranslation;

import java.util.function.Predicate;

public class HeroTranslationByHeroCodePredicate implements Predicate<HeroTranslation>
{
    private final String heroCode;

    public HeroTranslationByHeroCodePredicate(String heroCode)
    {
        this.heroCode = heroCode;
    }

    @Override
    public boolean test(HeroTranslation heroTranslation)
    {
        return heroTranslation.getHeroCode().equals(this.heroCode);
    }
}
