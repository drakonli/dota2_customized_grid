package drakonli.dota2.hero_grid_customizer.domain.models;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
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
        heroGridCustomization.getHeroNameCustomizations().add(heroNameCustomizationOne);

        List<HeroNameCustomization> expectedHeroNameCustomizationList = new ArrayList<>();
        expectedHeroNameCustomizationList.add(heroNameCustomizationOne);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(
                expectedHeroNameCustomizationList,
                heroGridCustomization.getHeroNameCustomizations()
        ));

        assertEquals("someName", heroGridCustomization.getName());
        assertTrue(null != heroGridCustomization.getCreateDate());

        HeroGridCustomization heroGridCustomizationDefaultConstructor = new HeroGridCustomization();

        assertTrue(EqualsBuilder.reflectionEquals(
                new ArrayList<>(),
                heroGridCustomizationDefaultConstructor.getHeroNameCustomizations()
        ));
    }

    @Test
    public void testFindHeroNameCustomizationByHeroNameUUID()
    {
        HeroNameCustomization heroNameCustomizationOne = new HeroNameCustomization("someName", "someValue");
        HeroNameCustomization heroNameCustomizationTwo = new HeroNameCustomization("someName1", "someValue1");

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization("someName");
        heroGridCustomization.getHeroNameCustomizations().add(heroNameCustomizationOne);
        heroGridCustomization.getHeroNameCustomizations().add(heroNameCustomizationTwo);

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