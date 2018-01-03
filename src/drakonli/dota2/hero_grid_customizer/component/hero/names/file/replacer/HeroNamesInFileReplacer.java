package drakonli.dota2.hero_grid_customizer.component.hero.names.file.replacer;

import drakonli.component.file.chooser.InvalidFileFormatException;
import drakonli.component.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.component.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt.Dota2TranslationsFileHeroTranslationsLineEditorQualifier;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroNamesInFileReplacer
{
    private static final String HERO_TRANSLATION_MATCH_PATTERN = "\"(npc_dota_hero_[^_]*)\".*\"(.*)\"";

    private TxtFileByLineEditorInterface txtFileByLineEditor;

    public HeroNamesInFileReplacer(TxtFileByLineEditorInterface txtFileByLineEditor)
    {
        this.txtFileByLineEditor = txtFileByLineEditor;
    }

    public void replaceHeroNames(File heroNamesFile, List<HeroTranslation> heroTranslations)
            throws InvalidFileFormatException, IOException
    {
        Map<String, String> heroCodeToHeroNameMap = this.createMapOfHeroCodesToNames(heroTranslations);

        Dota2TranslationsFileHeroTranslationsLineEditorQualifier editorQualifier =
                new Dota2TranslationsFileHeroTranslationsLineEditorQualifier(
                        heroCodeToHeroNameMap,
                        HeroNamesInFileReplacer.HERO_TRANSLATION_MATCH_PATTERN
                );

        try {
            this.txtFileByLineEditor.edit(heroNamesFile, editorQualifier, editorQualifier);
        } catch (NoLineQualifiedForEditException e) {
            throw new InvalidFileFormatException("Chosen file has wrong format. Please, choose dota2 translation file");
        }
    }

    private Map<String, String> createMapOfHeroCodesToNames(List<HeroTranslation> heroTranslations)
    {
        Map<String, String> heroCodeToHeroName = new HashMap<>();

        for (HeroTranslation heroTranslation : heroTranslations) {
            heroCodeToHeroName.put(heroTranslation.getHeroCode(), heroTranslation.getHeroName());
        }

        return heroCodeToHeroName;
    }
}
