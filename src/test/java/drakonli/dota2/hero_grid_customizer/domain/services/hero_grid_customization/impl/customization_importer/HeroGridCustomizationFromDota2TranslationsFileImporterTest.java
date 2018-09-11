package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_importer;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.exceptions.Dota2InvalidFileFormatException;
import drakonli.jcomponents.file.IBufferedFileReaderFactory;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroGridCustomizationFromDota2TranslationsFileImporterTest
{
    @Mock
    IBufferedFileReaderFactory            readerFactoryMock;
    @Mock
    IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractorMock;
    @Mock
    File                                  fileMock;
    @Mock
    BufferedReader                        readerMock;

    protected HeroGridCustomizationFromDota2TranslationsFileImporter testedImporter;

    @Before
    public void setUp() throws IOException
    {

        this.testedImporter = new HeroGridCustomizationFromDota2TranslationsFileImporter(
                this.readerFactoryMock,
                this.heroNameCustomizationByLineExtractorMock
        );

        when(this.readerFactoryMock.createFileReader(this.fileMock)).thenReturn(this.readerMock);
    }

    @Test
    public void importCustomization() throws Dota2InvalidFileFormatException, IOException
    {
        HeroNameCustomization hnc1 = mock(HeroNameCustomization.class);
        HeroNameCustomization hnc2 = mock(HeroNameCustomization.class);
        HeroNameCustomization hnc3 = mock(HeroNameCustomization.class);

        when(this.readerMock.readLine())
                .thenReturn("some line")
                .thenReturn("some line 2")
                .thenReturn("")
                .thenReturn("some line 3")
                .thenReturn("some line 4")
                .thenReturn(null);

        when(this.heroNameCustomizationByLineExtractorMock.extractByLine("some line")).thenReturn(Optional.of(hnc1));
        when(this.heroNameCustomizationByLineExtractorMock.extractByLine("some line 2")).thenReturn(Optional.of(hnc2));
        when(this.heroNameCustomizationByLineExtractorMock.extractByLine("some line 3")).thenReturn(Optional.of(hnc3));
        when(this.heroNameCustomizationByLineExtractorMock.extractByLine("some line 4")).thenReturn(Optional.empty());

        HeroGridCustomization actualHeroGridCustomization = this.testedImporter.importCustomization(this.fileMock);

        verify(this.readerMock).close();

        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization();
        expectedHeroGridCustomization.add(hnc1);
        expectedHeroGridCustomization.add(hnc2);
        expectedHeroGridCustomization.add(hnc3);

        assertTrue(EqualsBuilder.reflectionEquals(expectedHeroGridCustomization, actualHeroGridCustomization));
    }

    @Test
    public void importCustomizationWithException() throws IOException
    {
        try {
            this.testedImporter.importCustomization(this.fileMock);

            fail("Expected " + Dota2InvalidFileFormatException.class);
        } catch (Dota2InvalidFileFormatException e) {
            assertEquals("File is not a dota2 translations file", e.getMessage());
        }

        verify(this.readerMock).close();
    }
}