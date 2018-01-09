package drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt;

import drakonli.component.file.editor.txt.TxtLineEditorInterface;
import drakonli.component.file.editor.txt.TxtLineForEditQualifierInterface;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationViewModelByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.util.Map;

public class Dota2TranslationsFileHeroTranslationsLineEditorQualifier implements
        TxtLineEditorInterface,
        TxtLineForEditQualifierInterface
{
    private HeroTranslationViewModel lastMatchedHeroTranslation;

    private final Map<String, String> heroCodeToHeroNameMap;
    private final HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditorQualifier(
            Map<String, String> heroCodeToHeroNameMap,
            HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor
    )
    {
        this.heroCodeToHeroNameMap = heroCodeToHeroNameMap;
        this.heroTranslationViewModelExtractor = heroTranslationViewModelExtractor;
    }

    @Override
    public Boolean isLineQualifiedForEdit(String line)
    {
        if (line.isEmpty()) {
            return false;
        }

        this.lastMatchedHeroTranslation = this.heroTranslationViewModelExtractor.extractByLine(line);

        return null != this.lastMatchedHeroTranslation;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.lastMatchedHeroTranslation) {
            return line;
        }

        String heroCode = this.lastMatchedHeroTranslation.getHeroCode();
        String heroName = this.lastMatchedHeroTranslation.getHeroName();

        String newHeroName = this.heroCodeToHeroNameMap.get(heroCode);

        if (null == newHeroName) {
            return line;
        }

        return line.replace("\"" + heroName + "\"", "\"" + newHeroName + "\"");
    }
}
