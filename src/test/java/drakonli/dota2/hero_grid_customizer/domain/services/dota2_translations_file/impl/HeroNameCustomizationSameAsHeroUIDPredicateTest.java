package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeroNameCustomizationSameAsHeroUIDPredicateTest
{

    @Test
    public void test()
    {
        HeroNameCustomizationSameAsHeroUIDPredicate testedPredicate =
                new HeroNameCustomizationSameAsHeroUIDPredicate("someHeroCode");

        assertTrue(testedPredicate.test(new HeroNameCustomization("someHeroCode", "someHeroUID")));
        assertFalse(testedPredicate.test(new HeroNameCustomization("anotherHeroCode", "someHeroUID")));
    }
}