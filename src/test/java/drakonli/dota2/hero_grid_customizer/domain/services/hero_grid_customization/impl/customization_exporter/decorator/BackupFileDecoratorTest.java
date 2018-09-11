package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.IFileBackuper;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BackupFileDecoratorTest
{
    @Mock
    File                                 fileMock;
    @Mock
    HeroGridCustomization                heroGridCustomizationMock;
    @Mock
    IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporterMock;
    @Mock
    IFileBackuper                        backuperMock;

    protected BackupFileDecorator testedDecorator;

    @Before
    public void setUp()
    {
        this.testedDecorator = new BackupFileDecorator(this.heroGridCustomizationToFileExporterMock, this.backuperMock);
    }

    @Test
    public void export() throws InvalidFileFormatException, IOException, ExportException
    {
        this.testedDecorator.export(this.fileMock, this.heroGridCustomizationMock);

        verify(this.backuperMock).backup(this.fileMock);
        verify(this.heroGridCustomizationToFileExporterMock).export(this.fileMock, this.heroGridCustomizationMock);
    }
}