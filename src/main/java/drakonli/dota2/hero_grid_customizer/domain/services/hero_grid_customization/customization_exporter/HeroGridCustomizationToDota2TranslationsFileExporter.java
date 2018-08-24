package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.exception.Dota2InvalidFileFormatException;
import drakonli.jcomponents.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;

import java.io.File;
import java.io.IOException;

public class HeroGridCustomizationToDota2TranslationsFileExporter implements IHeroGridCustomizationToFileExporter
{
    private final TxtFileByLineEditorInterface          txtFileByLineEditor;
    private final IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor;

    public HeroGridCustomizationToDota2TranslationsFileExporter(
            TxtFileByLineEditorInterface txtFileByLineEditor,
            IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor
    )
    {
        this.txtFileByLineEditor = txtFileByLineEditor;
        this.heroNameCustomizationByLineExtractor = heroNameCustomizationByLineExtractor;
    }

    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws Dota2InvalidFileFormatException, IOException
    {
        Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate lineEditorAndPredicate =
                new Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
                        heroGridCustomization,
                        this.heroNameCustomizationByLineExtractor
                );

        try {
            this.txtFileByLineEditor.edit(file, lineEditorAndPredicate, lineEditorAndPredicate);
        }
        catch (NoLineQualifiedForEditException e) {
            throw new Dota2InvalidFileFormatException();
        }
    }
}
