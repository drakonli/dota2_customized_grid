package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StoreHeroGridCustomizationDecoratorTest
{
    @Mock
    File                                 fileMock;
    @Mock
    HeroGridCustomization                heroGridCustomizationMock;
    @Mock
    IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporterMock;
    @Mock
    IHeroGridCustomizationStorage        heroGridCustomizationStorageMock;

    protected StoreHeroGridCustomizationDecorator testedDecorator;

    @Before
    public void setUp()
    {
        this.testedDecorator = new StoreHeroGridCustomizationDecorator(
                this.heroGridCustomizationToFileExporterMock,
                this.heroGridCustomizationStorageMock
        );
    }

    @Test
    public void export() throws InvalidFileFormatException, IOException, ExportException, StorageException
    {
        this.testedDecorator.export(this.fileMock, this.heroGridCustomizationMock);

        verify(this.heroGridCustomizationToFileExporterMock).export(this.fileMock, this.heroGridCustomizationMock);
        verify(this.heroGridCustomizationStorageMock).store(this.heroGridCustomizationMock);
    }

    @Test
    public void exportWithException() throws InvalidFileFormatException, IOException, ExportException, StorageException
    {
        doThrow(StorageException.class).when(this.heroGridCustomizationStorageMock)
                                       .store(this.heroGridCustomizationMock);

        try {
            this.testedDecorator.export(this.fileMock, this.heroGridCustomizationMock);

            fail("Expected " + ExportException.class);
        } catch (ExportException e) {
            assertEquals("Export error occurred", e.getMessage());
        }

        verify(this.heroGridCustomizationToFileExporterMock).export(this.fileMock, this.heroGridCustomizationMock);
        verify(this.heroGridCustomizationStorageMock).store(this.heroGridCustomizationMock);
    }
}