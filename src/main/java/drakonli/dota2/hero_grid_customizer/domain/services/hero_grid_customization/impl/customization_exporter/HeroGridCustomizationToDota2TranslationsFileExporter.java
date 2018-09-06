package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.ILineEditorByGridCustomizationFactory;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.exceptions.Dota2InvalidFileFormatException;
import drakonli.jcomponents.ITxtLineEditor;
import drakonli.jcomponents.ITxtLinePredicate;
import drakonli.jcomponents.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.file.ITxtFileByLineEditor;

import java.io.File;
import java.io.IOException;

public class HeroGridCustomizationToDota2TranslationsFileExporter implements IHeroGridCustomizationToFileExporter
{
    private ITxtLinePredicate                     linePredicate;
    private ILineEditorByGridCustomizationFactory lineEditorByGridCustomizationFactory;
    private ITxtFileByLineEditor                  fileByLineEditor;

    public HeroGridCustomizationToDota2TranslationsFileExporter(
            ITxtLinePredicate linePredicate,
            ILineEditorByGridCustomizationFactory lineEditorByGridCustomizationFactory,
            ITxtFileByLineEditor fileByLineEditor
    )
    {
        this.linePredicate = linePredicate;
        this.lineEditorByGridCustomizationFactory = lineEditorByGridCustomizationFactory;
        this.fileByLineEditor = fileByLineEditor;
    }

    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws Dota2InvalidFileFormatException, IOException
    {
        ITxtLineEditor lineEditor = this.lineEditorByGridCustomizationFactory.create(heroGridCustomization);

        try {
            this.fileByLineEditor.edit(file, lineEditor, this.linePredicate);
        } catch (NoLineQualifiedForEditException e) {
            throw new Dota2InvalidFileFormatException();
        }
    }
}
