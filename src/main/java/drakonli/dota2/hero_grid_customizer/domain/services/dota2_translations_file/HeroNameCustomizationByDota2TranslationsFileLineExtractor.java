package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroNameCustomizationByDota2TranslationsFileLineExtractor implements IHeroNameCustomizationByLineExtractor
{
    private static final String HERO_TRANSLATION_MATCH_PATTERN = "\"(npc_dota_hero_[^_]*)\".*\"(.*)\"";
    private static final String[] HERO_TRANSLATION_UNSUITED_SUFFIXES = {
            "_bio", "_radiant", "_dire", "_hype", "_bio"
    };

    @Override
    public HeroNameCustomization extractByLine(String line)
    {
        Pattern pattern = Pattern.compile(HERO_TRANSLATION_MATCH_PATTERN);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return null;
        }

        String heroCode = matcher.group(1);

        if (this.heroCodeEndsWithUnsuitedSuffix(heroCode)) {
            return null;
        }

        String heroName = matcher.group(2);

        return new HeroNameCustomization(heroCode, heroName);
    }

    private Boolean heroCodeEndsWithUnsuitedSuffix(String heroCode)
    {
        for (String suffix : HERO_TRANSLATION_UNSUITED_SUFFIXES) {
            if (heroCode.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }
}
