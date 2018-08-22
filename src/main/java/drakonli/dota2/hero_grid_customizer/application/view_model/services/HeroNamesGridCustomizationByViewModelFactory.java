package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;

import java.util.List;

public class HeroNamesGridCustomizationByViewModelFactory
{
    public HeroNamesGridCustomization create(List<HeroTranslationViewModel> heroGridViewModels)
    {
        HeroNamesGridCustomization heroNamesGridCustomization = new HeroNamesGridCustomization();

        for (HeroTranslationViewModel heroTranslationViewModel : heroGridViewModels) {
            heroNamesGridCustomization.add(
                    new HeroNameCustomization(
                            heroTranslationViewModel.getHeroCode(),
                            heroTranslationViewModel.getHeroName()
                    )
            );
        }

        return heroNamesGridCustomization;
    }
}
