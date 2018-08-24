package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.editor.txt.Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.jcomponents.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;

import java.io.File;
import java.io.IOException;

public class HeroGridCustomizationToDota2TranslationsFileExporter implements IHeroGridCustomizationToFileExporter
{
    private TxtFileByLineEditorInterface txtFileByLineEditor;
    private final HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor;

    public HeroGridCustomizationToDota2TranslationsFileExporter(
            TxtFileByLineEditorInterface txtFileByLineEditor,
            HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor
    )
    {
        this.txtFileByLineEditor = txtFileByLineEditor;
        this.heroTranslationByFileLineExtractor = heroTranslationByFileLineExtractor;
    }

    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws Dota2InvalidFileFormatException, IOException
    {
        Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate lineEditorAndPredicate =
                new Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
                        heroGridCustomization,
                        this.heroTranslationByFileLineExtractor
                );

        try {
            this.txtFileByLineEditor.edit(file, lineEditorAndPredicate, lineEditorAndPredicate);
        } catch (NoLineQualifiedForEditException e) {
            throw new Dota2InvalidFileFormatException();
        }
    }
}
