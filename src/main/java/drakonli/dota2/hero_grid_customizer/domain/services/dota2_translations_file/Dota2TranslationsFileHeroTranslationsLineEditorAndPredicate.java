package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
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
    private HeroNameCustomization heroNameCustomizationInCurrentLine;

    private final HeroGridCustomization                 heroGridCustomization;
    private final IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor;

    public Dota2TranslationsFileHeroTranslationsLineEditorAndPredicate(
            HeroGridCustomization heroGridCustomization,
            IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor
    )
    {
        this.heroGridCustomization = heroGridCustomization;
        this.heroTranslationByLineExtractor = heroTranslationByLineExtractor;
    }

    @Override
    public boolean test(String line)
    {
        if (line.isEmpty()) {
            return false;
        }

        this.heroNameCustomizationInCurrentLine = this.heroTranslationByLineExtractor.extractByLine(line);

        return null != this.heroNameCustomizationInCurrentLine;
    }

    @Override
    public String editLine(String line)
    {
        if (null == this.heroNameCustomizationInCurrentLine) {
            return line;
        }

        Optional<HeroNameCustomization> optionalHeroNameCustomizationFromGrid =
                this.findHeroNameCustomizationInGridByHeroNameUUID(
                        this.heroNameCustomizationInCurrentLine.getHeroNameUID()
                );

        if (!optionalHeroNameCustomizationFromGrid.isPresent()) {
            return line;
        }

        return this.replaceHeroNameInLineByHeroCustomizationFromGrid(line, optionalHeroNameCustomizationFromGrid.get());
    }

    private Optional<HeroNameCustomization> findHeroNameCustomizationInGridByHeroNameUUID(String heroNameUUID)
    {
        return this.heroGridCustomization
                .stream()
                .filter(
                        new HeroNameCustomizationSameAsHeroUIDPredicate(heroNameUUID)
                )
                .findFirst();
    }

    private String replaceHeroNameInLineByHeroCustomizationFromGrid(
            String line,
            HeroNameCustomization heroNameCustomization
    )
    {
        return line.replace(
                "\"" + this.heroNameCustomizationInCurrentLine.getHeroName() + "\"",
                "\"" + heroNameCustomization.getHeroName() + "\""
        );
    }
}
