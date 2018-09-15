package drakonli.dota2.hero_grid_customizer.domain.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class HeroGridCustomizationTest
{
    @Test
    public void testIsListOfCustomizations()
    {
        HeroNameCustomization heroNameCustomizationOne = new HeroNameCustomization("1", "2");

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization("someName");
        heroGridCustomization.add(heroNameCustomizationOne);

        List<HeroNameCustomization> expectedHeroNameCustomizationList = new ArrayList<>();
        expectedHeroNameCustomizationList.add(heroNameCustomizationOne);

        assertEquals(expectedHeroNameCustomizationList, heroGridCustomization);
        assertEquals("someName", heroGridCustomization.getName());
    }

    @Test
    public void testFindHeroNameCustomizationByHeroNameUUID()
    {
        HeroNameCustomization heroNameCustomizationOne = new HeroNameCustomization("someName", "someValue");
        HeroNameCustomization heroNameCustomizationTwo = new HeroNameCustomization("someName1", "someValue1");

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization("someName");
        heroGridCustomization.add(heroNameCustomizationOne);
        heroGridCustomization.add(heroNameCustomizationTwo);

        Optional<HeroNameCustomization> actualHeroNameCustomizationOne
                = heroGridCustomization.findHeroNameCustomizationByHeroNameUUID("someName");

        Optional<HeroNameCustomization> actualHeroNameCustomizationTwo
                = heroGridCustomization.findHeroNameCustomizationByHeroNameUUID("someName1");

        assertTrue(actualHeroNameCustomizationOne.isPresent());
        assertEquals("someValue", actualHeroNameCustomizationOne.get().getHeroName());

        assertTrue(actualHeroNameCustomizationTwo.isPresent());
        assertEquals("someValue1", actualHeroNameCustomizationTwo.get().getHeroName());

        assertFalse(heroGridCustomization.findHeroNameCustomizationByHeroNameUUID("invalid name").isPresent());
        assertEquals("someName", heroGridCustomization.getName());
    }
}