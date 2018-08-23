package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class HeroGridCustomizationToViewModelsMapper
{
    public void map(
            HeroGridCustomization heroGridCustomization,
            List<HeroTranslationViewModel> heroTranslationViewModels
    )
    {
        heroTranslationViewModels.clear();

        for (HeroNameCustomization heroNameCustomization : heroGridCustomization) {
            heroTranslationViewModels.add(
                    new HeroTranslationViewModel(
                            heroNameCustomization.getHeroName(),
                            heroNameCustomization.getHeroNameUID()
                    )
            );
        }
    }
}
