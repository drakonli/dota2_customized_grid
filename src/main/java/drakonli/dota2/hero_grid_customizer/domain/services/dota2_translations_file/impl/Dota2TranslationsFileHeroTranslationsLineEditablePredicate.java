package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.jcomponents.ITxtLinePredicate;

/**
 * Tests if current txt line contains dota2 hero customization data
 */
public class Dota2TranslationsFileHeroTranslationsLineEditablePredicate implements ITxtLinePredicate
{
    private final IHeroNameCustomizationByLineExtractor heroCustomizationByLineExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditablePredicate(
            IHeroNameCustomizationByLineExtractor heroCustomizationByLineExtractor
    )
    {
        this.heroCustomizationByLineExtractor = heroCustomizationByLineExtractor;
    }

    @Override
    public boolean test(String line)
    {
        return !line.isEmpty()
                && this.heroCustomizationByLineExtractor.extractByLine(line).isPresent();
    }
}
