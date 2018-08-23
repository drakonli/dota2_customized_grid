package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class HeroGridCustomizationByViewModelsFactory
{
    public HeroGridCustomization create(List<HeroTranslationViewModel> heroGridViewModels)
    {
        HeroGridCustomization heroGridCustomization = new HeroGridCustomization();

        for (HeroTranslationViewModel heroTranslationViewModel : heroGridViewModels) {
            heroGridCustomization.add(
                    new HeroNameCustomization(
                            heroTranslationViewModel.getHeroCode(),
                            heroTranslationViewModel.getHeroName()
                    )
            );
        }

        return heroGridCustomization;
    }
}
