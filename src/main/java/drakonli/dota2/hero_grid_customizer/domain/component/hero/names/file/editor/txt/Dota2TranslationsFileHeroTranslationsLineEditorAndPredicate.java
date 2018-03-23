package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.editor.txt;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.predicate.HeroTranslationByHeroCodePredicate;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.jcomponents.file.editor.txt.TxtLineEditorInterface;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.util.List;
import java.util.Optional;

/**
 * This class is used to test a line in a file to be a dota2 hero translation line and to edit that line using the
 * "new" translations list. So that the current line that holds a translation for specific hero would be replaced by
 * a new line that's currently in the "heroTranslations" List.
 *
 * Two interfaces are joined for optimization purposes - so that when we wouldn't need to extract the same translation
 * twice from a line.
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
