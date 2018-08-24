package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.editor.txt.Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroNameCustomizationByDota2TranslationsFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.exception.Dota2InvalidFileFormatException;
import drakonli.jcomponents.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;

import java.io.File;
import java.io.IOException;

public class HeroGridCustomizationToDota2TranslationsFileExporter implements IHeroGridCustomizationToFileExporter
{
    private       TxtFileByLineEditorInterface txtFileByLineEditor;
    private final HeroNameCustomizationByDota2TranslationsFileLineExtractor
                                               heroNameCustomizationByDota2TranslationsFileLineExtractor;

    public HeroGridCustomizationToDota2TranslationsFileExporter(
            TxtFileByLineEditorInterface txtFileByLineEditor,
            HeroNameCustomizationByDota2TranslationsFileLineExtractor heroNameCustomizationByDota2TranslationsFileLineExtractor
    )
    {
        this.txtFileByLineEditor = txtFileByLineEditor;
        this.heroNameCustomizationByDota2TranslationsFileLineExtractor
                = heroNameCustomizationByDota2TranslationsFileLineExtractor;
    }

    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws Dota2InvalidFileFormatException, IOException
    {
        Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate lineEditorAndPredicate =
                new Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
                        heroGridCustomization,
                        this.heroNameCustomizationByDota2TranslationsFileLineExtractor
                );

        try {
            this.txtFileByLineEditor.edit(file, lineEditorAndPredicate, lineEditorAndPredicate);
        } catch (NoLineQualifiedForEditException e) {
            throw new Dota2InvalidFileFormatException();
        }
    }
}
