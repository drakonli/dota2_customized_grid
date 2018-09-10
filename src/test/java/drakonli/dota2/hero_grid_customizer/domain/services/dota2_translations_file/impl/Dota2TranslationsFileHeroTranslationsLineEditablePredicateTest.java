package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Dota2TranslationsFileHeroTranslationsLineEditablePredicateTest
{
    @Mock
    IHeroNameCustomizationByLineExtractor extractorMock;

    @Mock
    HeroNameCustomization heroNameCustomizationMock;

    @Test
    public void test()
    {
        when(this.extractorMock.extractByLine("someLine")).thenReturn(Optional.of(this.heroNameCustomizationMock));
        when(this.extractorMock.extractByLine("someLine2")).thenReturn(Optional.empty());

        Dota2TranslationsFileHeroTranslationsLineEditablePredicate testedPredicate =
                new Dota2TranslationsFileHeroTranslationsLineEditablePredicate(this.extractorMock);

        assertTrue(testedPredicate.test("someLine"));
        assertFalse(testedPredicate.test("someLine2"));

        verify(this.extractorMock).extractByLine("someLine");
        verify(this.extractorMock).extractByLine("someLine2");
    }

    @Test
    public void testEmptyString()
    {
        Dota2TranslationsFileHeroTranslationsLineEditablePredicate testedPredicate =
                new Dota2TranslationsFileHeroTranslationsLineEditablePredicate(this.extractorMock);

        assertFalse(testedPredicate.test(""));

        verify(this.extractorMock, never()).extractByLine(anyString());
    }
}