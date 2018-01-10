package drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt;

import drakonli.component.file.editor.txt.TxtLineEditorInterface;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.matcher.LineToEditMatcherInterface;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.util.Map;

/**
 * Two interfaces are joined for optimization purposes - the result of pattern matching in extractor should be
 * used in
 */
public class Dota2TranslationsFileHeroTranslationsLineEditorAndMatcher implements
        TxtLineEditorInterface,
        LineToEditMatcherInterface
{
    private HeroTranslation lastMatchedHeroTranslation;

    private final Map<String, String> heroCodeToHeroNameMap;
    private final HeroTranslationByFileLineExtractor heroTranslationByLineExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditorAndMatcher(
            Map<String, String> heroCodeToHeroNameMap,
            HeroTranslationByFileLineExtractor heroTranslationExtractor
    )
    {
        this.heroCodeToHeroNameMap = heroCodeToHeroNameMap;
        this.heroTranslationByLineExtractor = heroTranslationExtractor;
    }

    @Override
    public Boolean match(String line)
    {
        if (line.isEmpty()) {
            return false;
        }

        this.lastMatchedHeroTranslation = this.heroTranslationByLineExtractor.extractByLine(line);

        return null != this.lastMatchedHeroTranslation;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.lastMatchedHeroTranslation) {
            return line;
        }

        String newHeroName = this.heroCodeToHeroNameMap.get(
                this.lastMatchedHeroTranslation.getHeroCode()
        );

        if (null == newHeroName) {
            return line;
        }

        return line.replace("\"" + this.lastMatchedHeroTranslation.getHeroName() + "\"", "\"" + newHeroName + "\"");
    }
}
