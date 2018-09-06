package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
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
public class Dota2HeroTranslationsLineByGridCustomizationEditorTest
{
    @Mock
    HeroNameCustomization heroNameCustomizationFromLineMock;

    @Mock
    HeroNameCustomization heroNameCustomizationFromGridMock;

    @Mock
    HeroGridCustomization heroGridCustomizationMock;

    @Mock
    IHeroNameCustomizationByLineExtractor heroCustomizationByLineExtractorMock;

    Dota2HeroTranslationsLineByGridCustomizationEditor testedEditor;

    @Test
    public void editLine()
    {
        String lineToEdit = "some text \"some hero name\" more text";
        String expectedLine = "some text \"some new hero name\" more text";

        Optional<HeroNameCustomization> customizationFromLine = Optional.of(this.heroNameCustomizationFromLineMock);
        Optional<HeroNameCustomization> customizationFromGrid = Optional.of(this.heroNameCustomizationFromGridMock);

        this.testedEditor = new Dota2HeroTranslationsLineByGridCustomizationEditor(
                this.heroGridCustomizationMock,
                this.heroCustomizationByLineExtractorMock
        );

        when(this.heroCustomizationByLineExtractorMock.extractByLine(lineToEdit)).thenReturn(customizationFromLine);
        when(this.heroNameCustomizationFromLineMock.getHeroName()).thenReturn("some hero name");
        when(this.heroNameCustomizationFromLineMock.getHeroNameUID()).thenReturn("someUUID");
        when(this.heroNameCustomizationFromGridMock.getHeroName()).thenReturn("some new hero name");
        when(this.heroGridCustomizationMock.findHeroNameCustomizationByHeroNameUUID("someUUID"))
                .thenReturn(customizationFromGrid);

        String actualLine = this.testedEditor.editLine(lineToEdit);

        assertEquals(expectedLine, actualLine);
    }

    @Test
    public void editLineNotFoundInGrid()
    {
        String lineToEdit = "some text \"some hero name\" more text";

        Optional<HeroNameCustomization> customizationFromLine = Optional.of(this.heroNameCustomizationFromLineMock);
        Optional<HeroNameCustomization> customizationFromGrid = Optional.empty();

        this.testedEditor = new Dota2HeroTranslationsLineByGridCustomizationEditor(
                this.heroGridCustomizationMock,
                this.heroCustomizationByLineExtractorMock
        );

        when(this.heroCustomizationByLineExtractorMock.extractByLine(lineToEdit)).thenReturn(customizationFromLine);
        when(this.heroNameCustomizationFromLineMock.getHeroName()).thenReturn("some hero name");
        when(this.heroNameCustomizationFromLineMock.getHeroNameUID()).thenReturn("someUUID");
        when(this.heroGridCustomizationMock.findHeroNameCustomizationByHeroNameUUID("someUUID"))
                .thenReturn(customizationFromGrid);

        String actualLine = this.testedEditor.editLine(lineToEdit);

        assertEquals(lineToEdit, actualLine);
        verify(this.heroNameCustomizationFromGridMock, never()).getHeroName();
        verify(this.heroNameCustomizationFromLineMock, never()).getHeroName();
    }

    @Test
    public void editLineNotFoundInLine()
    {
        String lineToEdit = "some text \"some hero name\" more text";

        this.testedEditor = new Dota2HeroTranslationsLineByGridCustomizationEditor(
                this.heroGridCustomizationMock,
                this.heroCustomizationByLineExtractorMock
        );

        when(this.heroCustomizationByLineExtractorMock.extractByLine(lineToEdit)).thenReturn(Optional.empty());

        String actualLine = this.testedEditor.editLine(lineToEdit);

        assertEquals(lineToEdit, actualLine);
        verify(this.heroGridCustomizationMock, never()).findHeroNameCustomizationByHeroNameUUID(anyString());
        verify(this.heroNameCustomizationFromLineMock, never()).getHeroNameUID();
        verify(this.heroNameCustomizationFromGridMock, never()).getHeroName();
        verify(this.heroNameCustomizationFromLineMock, never()).getHeroName();
    }
}