package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.jcomponents.ITxtLineEditor;

import java.util.Optional;

public class Dota2HeroTranslationsLineByGridCustomizationEditor implements ITxtLineEditor
{
    private final HeroGridCustomization                 heroGridCustomization;
    private final IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor;

    public Dota2HeroTranslationsLineByGridCustomizationEditor(
            HeroGridCustomization heroGridCustomization,
            IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor
    )
    {
        this.heroGridCustomization = heroGridCustomization;
        this.heroTranslationByLineExtractor = heroTranslationByLineExtractor;
    }

    @Override
    public String editLine(String line)
    {
        Optional<HeroNameCustomization> optionalHeroNameCustomizationFromLine =
                this.heroTranslationByLineExtractor.extractByLine(line);

        if (!optionalHeroNameCustomizationFromLine.isPresent()) {
            return line;
        }

        HeroNameCustomization heroNameCustomizationFromLine = optionalHeroNameCustomizationFromLine.get();

        Optional<HeroNameCustomization> optionalHeroNameCustomizationFromGrid =
                this.findHeroNameCustomizationInGridByHeroNameUUID(heroNameCustomizationFromLine.getHeroNameUID());

        if (!optionalHeroNameCustomizationFromGrid.isPresent()) {
            return line;
        }

        HeroNameCustomization heroNameCustomizationFromGrid = optionalHeroNameCustomizationFromGrid.get();

        return line.replace(
                "\"" + heroNameCustomizationFromLine.getHeroName() + "\"",
                "\"" + heroNameCustomizationFromGrid.getHeroName() + "\""
        );
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
}
