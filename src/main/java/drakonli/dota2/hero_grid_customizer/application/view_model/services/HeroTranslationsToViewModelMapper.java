package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class HeroTranslationsToViewModelMapper
{
    public void map(
            List<HeroNameCustomization> heroNameCustomizations,
            List<HeroTranslationViewModel> heroTranslationViewModels
    )
    {
        heroTranslationViewModels.clear();

        for (HeroNameCustomization heroNameCustomization : heroNameCustomizations) {
            heroTranslationViewModels.add(
                    new HeroTranslationViewModel(
                            heroNameCustomization.getHeroName(),
                            heroNameCustomization.getHeroNameUID()
                    )
            );
        }
    }
}
