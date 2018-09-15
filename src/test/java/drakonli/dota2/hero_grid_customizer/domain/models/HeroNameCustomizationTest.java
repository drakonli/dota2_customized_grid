package drakonli.dota2.hero_grid_customizer.domain.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeroNameCustomizationTest
{
    @Test
    public void testGetters()
    {
        String uid = "some uuid";
        String name = "some name";

        HeroNameCustomization heroNameCustomization = new HeroNameCustomization(uid, name);

        assertEquals(heroNameCustomization.getHeroNameUID(), uid);
        assertEquals(heroNameCustomization.getHeroName(), name);

        assertTrue(null != new HeroNameCustomization());
    }
}