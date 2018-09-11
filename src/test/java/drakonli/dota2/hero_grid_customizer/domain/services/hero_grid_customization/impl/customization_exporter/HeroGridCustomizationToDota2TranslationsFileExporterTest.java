package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.ILineEditorByGridCustomizationFactory;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.exceptions.Dota2InvalidFileFormatException;
import drakonli.jcomponents.ITxtLineEditor;
import drakonli.jcomponents.ITxtLinePredicate;
import drakonli.jcomponents.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.file.ITxtFileByLineEditor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroGridCustomizationToDota2TranslationsFileExporterTest
{
    @Mock
    ITxtLinePredicate                     linePredicateMock;
    @Mock
    ILineEditorByGridCustomizationFactory lineEditorByGridCustomizationFactoryMock;
    @Mock
    ITxtFileByLineEditor                  fileByLineEditorMock;
    @Mock
    File                                  fileMock;
    @Mock
    HeroGridCustomization                 heroGridCustomizationMock;
    @Mock
    ITxtLineEditor                        lineEditorMock;

    protected HeroGridCustomizationToDota2TranslationsFileExporter testedExporter;

    @Before
    public void setUp()
    {
        doReturn(this.lineEditorMock).when(this.lineEditorByGridCustomizationFactoryMock)
                                     .create(this.heroGridCustomizationMock);

        this.testedExporter = new HeroGridCustomizationToDota2TranslationsFileExporter(
                this.linePredicateMock,
                this.lineEditorByGridCustomizationFactoryMock,
                this.fileByLineEditorMock
        );
    }

    @Test
    public void testExport() throws Dota2InvalidFileFormatException, IOException, NoLineQualifiedForEditException
    {
        this.testedExporter.export(this.fileMock, this.heroGridCustomizationMock);

        verify(this.fileByLineEditorMock).edit(this.fileMock, this.lineEditorMock, this.linePredicateMock);
    }

    @Test(expected = Dota2InvalidFileFormatException.class)
    public void testExportThrowsException()
            throws Dota2InvalidFileFormatException, IOException, NoLineQualifiedForEditException
    {
        doThrow(NoLineQualifiedForEditException.class).when(this.fileByLineEditorMock)
                                                      .edit(this.fileMock, this.lineEditorMock, this.linePredicateMock);

        this.testedExporter.export(this.fileMock, this.heroGridCustomizationMock);
    }
}