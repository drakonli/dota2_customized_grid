package drakonli.dota2.hero_grid_customizer.component.hero.names.file.replacer;

import drakonli.component.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.component.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt.Dota2TranslationsFileHeroTranslationsLineEditorAndMatcher;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationViewModelByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroNamesInFileReplacer
{
    private TxtFileByLineEditorInterface txtFileByLineEditor;
    private final HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor;

    public HeroNamesInFileReplacer(
            TxtFileByLineEditorInterface txtFileByLineEditor,
            HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor
    )
    {
        this.txtFileByLineEditor = txtFileByLineEditor;
        this.heroTranslationViewModelExtractor = heroTranslationViewModelExtractor;
    }

    public void replaceHeroNames(File heroNamesFile, List<HeroTranslation> heroTranslations)
            throws Dota2InvalidFileFormatException, IOException
    {
        Map<String, String> heroCodeToHeroNameMap = this.createMapOfHeroCodesToNames(heroTranslations);

        Dota2TranslationsFileHeroTranslationsLineEditorAndMatcher lineEditorAndQualifier =
                new Dota2TranslationsFileHeroTranslationsLineEditorAndMatcher(
                        heroCodeToHeroNameMap,
                        this.heroTranslationViewModelExtractor
                );

        try {
            this.txtFileByLineEditor.edit(heroNamesFile, lineEditorAndQualifier, lineEditorAndQualifier);
        } catch (NoLineQualifiedForEditException e) {
            throw new Dota2InvalidFileFormatException();
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
