package drakonli.dota2.hero_grid_customizer.domain.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeroGridCustomizationTest
{
    @Test
    public void testIsListOfCustomizations()
    {
        HeroNameCustomization heroNameCustomizationOne = new HeroNameCustomization("1", "2");

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization();
        heroGridCustomization.add(heroNameCustomizationOne);

        List<HeroNameCustomization> heroNameCustomizationList = new ArrayList<>();
        heroNameCustomizationList.add(heroNameCustomizationOne);

        assertEquals(heroGridCustomization, heroNameCustomizationList);
    }
}