package drakonli.dota2.hero_grid_customizer.component.hero.names.file.editor.txt;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.component.hero.names.predicate.HeroTranslationByHeroCodePredicate;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.jcomponents.file.editor.txt.TxtLineEditorInterface;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.util.List;
import java.util.Optional;

/**
 * Two interfaces are joined for optimization purposes
 */
public class Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate implements
        TxtLineEditorInterface,
        TxtLinePredicateInterface
{
    private HeroTranslation currentHeroTranslationInLine;

    private final List<HeroTranslation> heroTranslations;
    private final HeroTranslationByFileLineExtractor heroTranslationByLineExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
            List<HeroTranslation> heroTranslations,
            HeroTranslationByFileLineExtractor heroTranslationByLineExtractor
    )
    {
        this.heroTranslations = heroTranslations;
        this.heroTranslationByLineExtractor = heroTranslationByLineExtractor;
    }

    @Override
    public boolean test(String line)
    {
        if (line.isEmpty()) {
            return false;
        }

        this.currentHeroTranslationInLine = this.heroTranslationByLineExtractor.extractByLine(line);

        return null != this.currentHeroTranslationInLine;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.currentHeroTranslationInLine) {
            return line;
        }

        Optional<HeroTranslation> optionalHeroTranslation = this.heroTranslations
                .stream()
                .filter(new HeroTranslationByHeroCodePredicate(this.currentHeroTranslationInLine.getHeroCode()))
                .findFirst();

        if (!optionalHeroTranslation.isPresent()) {
            return line;
        }

        return line.replace(
                "\"" + this.currentHeroTranslationInLine.getHeroName() + "\"",
                "\"" + optionalHeroTranslation.get().getHeroName() + "\""
        );
    }
}
