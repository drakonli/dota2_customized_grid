package drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor;

import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroTranslationViewModelByFileLineExtractor
{
    private static final String HERO_TRANSLATION_MATCH_PATTERN = "\"(npc_dota_hero_[^_]*)\".*\"(.*)\"";
    private static final String[] HERO_TRANSLATION_FORBIDDEN_STRING_SUFFIXES = {
            "_bio", "_radiant", "_dire", "_hype", "_bio"
    };

    public HeroTranslationViewModel extractByLine(String line)
    {
        Pattern pattern = Pattern.compile(HERO_TRANSLATION_MATCH_PATTERN);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return null;
        }

        String heroCode = matcher.group(1);
        String heroName = matcher.group(2);

        if (this.heroCodeEndsWithForbiddenString(heroCode)) {
            return null;
        }

        return new HeroTranslationViewModel(heroName, heroCode);
    }

    private Boolean heroCodeEndsWithForbiddenString(String heroCode)
    {
        for (String suffix : HERO_TRANSLATION_FORBIDDEN_STRING_SUFFIXES) {
            if (heroCode.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }
}
