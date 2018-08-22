package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.editor.txt;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.predicate.HeroTranslationByHeroNameUIDPredicate;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.jcomponents.file.editor.txt.TxtLineEditorInterface;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.util.Optional;

/**
 * This class is used to test a line in a file to be a dota2 hero translation line and to edit that line using the
 * "new" translations list. So that the current line that holds a translation for specific hero would be replaced by
 * a new line that's currently in the "heroNameCustomizations" List.
 * <p>
 * Two interfaces are joined for optimization purposes - so that when we wouldn't need to extract the same translation
 * twice from a line.
 */
public class Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate implements
        TxtLineEditorInterface,
        TxtLinePredicateInterface
{
    private HeroNameCustomization currentHeroNameCustomizationInLine;

    private final HeroNamesGridCustomization heroNamesGridCustomization;
    private final HeroTranslationByFileLineExtractor heroTranslationByLineExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
            HeroNamesGridCustomization heroNamesGridCustomization,
            HeroTranslationByFileLineExtractor heroTranslationByLineExtractor
    )
    {
        this.heroNamesGridCustomization = heroNamesGridCustomization;
        this.heroTranslationByLineExtractor = heroTranslationByLineExtractor;
    }

    @Override
    public boolean test(String line)
    {
        if (line.isEmpty()) {
            return false;
        }

        this.currentHeroNameCustomizationInLine = this.heroTranslationByLineExtractor.extractByLine(line);

        return null != this.currentHeroNameCustomizationInLine;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.currentHeroNameCustomizationInLine) {
            return line;
        }

        Optional<HeroNameCustomization> optionalHeroTranslation = this.heroNamesGridCustomization
                .stream()
                .filter(
                        new HeroTranslationByHeroNameUIDPredicate(
                                this.currentHeroNameCustomizationInLine.getHeroNameUID()
                        )
                )
                .findFirst();

        if (!optionalHeroTranslation.isPresent()) {
            return line;
        }

        return line.replace(
                "\"" + this.currentHeroNameCustomizationInLine.getHeroName() + "\"",
                "\"" + optionalHeroTranslation.get().getHeroName() + "\""
        );
    }
}
