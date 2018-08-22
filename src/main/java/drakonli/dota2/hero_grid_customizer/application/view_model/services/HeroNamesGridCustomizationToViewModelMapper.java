package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;

import java.util.List;

public class HeroNamesGridCustomizationToViewModelMapper
{
    public void map(
            HeroNamesGridCustomization heroNamesGridCustomization,
            List<HeroTranslationViewModel> heroTranslationViewModels
    )
    {
        heroTranslationViewModels.clear();

        for (HeroNameCustomization heroNameCustomization : heroNamesGridCustomization) {
            heroTranslationViewModels.add(
                    new HeroTranslationViewModel(
                            heroNameCustomization.getHeroName(),
                            heroNameCustomization.getHeroNameUID()
                    )
            );
        }
    }
}
