package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class HeroNameCustomizationByDota2TranslationsFileLineExtractorTest
{
    @DataProvider
    public static Object[][] extractByLineSuccessDataProvider()
    {
        return new Object[][]{
                {
                        "\"npc_dota_hero_bane\" \"Bane\"",
                        new HeroNameCustomization("npc_dota_hero_bane", "Bane")
                },
                {
                        "\"npc_dota_hero_sven\" \"Sven\"",
                        new HeroNameCustomization("npc_dota_hero_sven", "Sven")
                },
                {
                        "\"npc_dota_hero_sand_king\" \"Sand King\"",
                        new HeroNameCustomization("npc_dota_hero_sand_king", "Sand King")
                },
                {
                        "\"npc_dota_hero_bloodseeker\" \"Bloodseeker\"",
                        new HeroNameCustomization("npc_dota_hero_bloodseeker", "Bloodseeker")
                },
                {
                        "\"npc_dota_hero_phantom_lancer\" \"Phantom Lancer\"",
                        new HeroNameCustomization("npc_dota_hero_phantom_lancer", "Phantom Lancer")
                }
        };
    }

    @DataProvider
    public static Object[][] extractByLineFailureDataProvider()
    {
        return new Object[][]{
                {"\"npc_dota_hero_bane_bio\" \"Bane\""},
                {"\"npc_dota_hero_bane_radiant\" \"Bane\""},
                {"\"npc_dota_hero_bane_dire\" \"Bane\""},
                {"\"npc_dota_hero_asd_asd_1123_hype\" \"Some bane hype\""},
                {"\"npc_dota_hero_phantom_lancer_bio\" \"Some pl bio\""},
                {"\"npc_dota_hero_winter_wyvern_bio\" \"Some ww bio\""},
                {"some line without any relevance"},
        };
    }

    @Test
    @UseDataProvider("extractByLineSuccessDataProvider")
    public void extractByLineSuccess(String lineToExtract, HeroNameCustomization expectedResult)
    {
        HeroNameCustomizationByDota2TranslationsFileLineExtractor testedExtractor =
                new HeroNameCustomizationByDota2TranslationsFileLineExtractor();

        Optional<HeroNameCustomization> actualResult = testedExtractor.extractByLine(lineToExtract);

        assertTrue(actualResult.isPresent());
        assertTrue(EqualsBuilder.reflectionEquals(expectedResult, actualResult.get()));
    }

    @Test
    @UseDataProvider("extractByLineFailureDataProvider")
    public void extractByLineFailure(String lineToExtract)
    {
        HeroNameCustomizationByDota2TranslationsFileLineExtractor testedExtractor =
                new HeroNameCustomizationByDota2TranslationsFileLineExtractor();

        Optional<HeroNameCustomization> actualResult = testedExtractor.extractByLine(lineToExtract);

        assertFalse(actualResult.isPresent());
    }
}