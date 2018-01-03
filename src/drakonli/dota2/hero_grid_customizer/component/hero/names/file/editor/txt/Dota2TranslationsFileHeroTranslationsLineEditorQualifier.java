package drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt;

import drakonli.component.file.editor.txt.TxtLineEditorInterface;
import drakonli.component.file.editor.txt.TxtLineForEditQualifierInterface;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dota2TranslationsFileHeroTranslationsLineEditorQualifier implements
        TxtLineEditorInterface,
        TxtLineForEditQualifierInterface
{
    private Matcher lastMatchedMatcher;

    private final Map<String, String> heroCodeToHeroNameMap;
    private final String heroTranslationMatchPattern;

    public Dota2TranslationsFileHeroTranslationsLineEditorQualifier(
            Map<String, String> heroCodeToHeroNameMap,
            String heroTranslationMatchPattern
    )
    {
        this.heroCodeToHeroNameMap = heroCodeToHeroNameMap;
        this.heroTranslationMatchPattern = heroTranslationMatchPattern;
    }

    @Override
    public Boolean isLineQualifiedForEdit(String line)
    {
        Pattern pattern = Pattern.compile(this.heroTranslationMatchPattern);
        Matcher matcher = pattern.matcher(line);
        Boolean isMatched = matcher.find();

        if (isMatched) {
            this.lastMatchedMatcher = matcher;
        }

        return isMatched;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.lastMatchedMatcher) {
            return line;
        }

        String hero_code = this.lastMatchedMatcher.group(1);
        String current_hero_name = this.lastMatchedMatcher.group(2);

        String newHeroName = this.heroCodeToHeroNameMap.get(hero_code);

        if (null != newHeroName) {
            return line.replace("\"" + current_hero_name + "\"", "\"" + newHeroName + "\"");
        }

        return line;
    }
}
