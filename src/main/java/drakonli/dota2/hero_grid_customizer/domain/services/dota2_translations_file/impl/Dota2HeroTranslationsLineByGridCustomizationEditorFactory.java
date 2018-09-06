package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.ILineEditorByGridCustomizationFactory;

public class Dota2HeroTranslationsLineByGridCustomizationEditorFactory implements ILineEditorByGridCustomizationFactory
{
    private final IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor;

    public Dota2HeroTranslationsLineByGridCustomizationEditorFactory(
            IHeroNameCustomizationByLineExtractor heroTranslationByLineExtractor
    )
    {
        this.heroTranslationByLineExtractor = heroTranslationByLineExtractor;
    }

    public Dota2HeroTranslationsLineByGridCustomizationEditor create(HeroGridCustomization heroGridCustomization)
    {
        return new Dota2HeroTranslationsLineByGridCustomizationEditor(
                heroGridCustomization,
                this.heroTranslationByLineExtractor
        );
    }
}
