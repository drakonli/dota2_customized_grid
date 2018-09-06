package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CachedHeroNameCustomizationByLineExtractorTest
{
    @Mock
    IHeroNameCustomizationByLineExtractor parentExtractorMock;

    @Mock
    HeroNameCustomization heroNameCustomizationMock;

    /**
     * everything in one method because tested class relies on it's own state
     */
    @Test
    public void extractByLine()
    {
        Optional<HeroNameCustomization> actualHeroNameCustomization;
        Optional<HeroNameCustomization> expectedHeroNameCustomization;

        CachedHeroNameCustomizationByLineExtractor testedExtractor
                = new CachedHeroNameCustomizationByLineExtractor(this.parentExtractorMock);

        // value is called and cached
        expectedHeroNameCustomization = Optional.of(this.heroNameCustomizationMock);
        when(this.parentExtractorMock.extractByLine("someLine")).thenReturn(expectedHeroNameCustomization);
        actualHeroNameCustomization = testedExtractor.extractByLine("someLine");
        assertEquals(expectedHeroNameCustomization, actualHeroNameCustomization);

        // verify that parent was called only once for two calls - that means value was cached
        actualHeroNameCustomization = testedExtractor.extractByLine("someLine");
        assertEquals(expectedHeroNameCustomization, actualHeroNameCustomization);
        verify(this.parentExtractorMock).extractByLine("someLine");

        // verify that parent was called two times for two calls with invalid value - the value is not cached
        expectedHeroNameCustomization = Optional.empty();
        when(this.parentExtractorMock.extractByLine("invalid line")).thenReturn(expectedHeroNameCustomization);

        actualHeroNameCustomization = testedExtractor.extractByLine("invalid line");
        assertEquals(expectedHeroNameCustomization, actualHeroNameCustomization);

        actualHeroNameCustomization = testedExtractor.extractByLine("invalid line");
        assertEquals(expectedHeroNameCustomization, actualHeroNameCustomization);

        verify(this.parentExtractorMock, times(2)).extractByLine("invalid line");
    }
}