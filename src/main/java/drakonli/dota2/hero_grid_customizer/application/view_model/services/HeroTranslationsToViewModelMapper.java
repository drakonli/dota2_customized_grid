package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.util.List;

public class HeroTranslationsToViewModelMapper
{
    public void map(
            List<HeroTranslation> heroTranslations,
            List<HeroTranslationViewModel> heroTranslationViewModels
    )
    {
        heroTranslationViewModels.clear();

        for (HeroTranslation heroTranslation : heroTranslations) {
            heroTranslationViewModels.add(
                    new HeroTranslationViewModel(
                            heroTranslation.getHeroName(),
                            heroTranslation.getHeroCode()
                    )
            );
        }
    }
}
