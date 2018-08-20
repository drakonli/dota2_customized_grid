package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.util.ArrayList;
import java.util.List;

public class HeroTranslationViewModelsToDomainModelMapper
{
    public List<HeroTranslation> mapToNewEntityList(List<HeroTranslationViewModel> heroTranslationViewModels)
    {
        List<HeroTranslation> heroTranslations = new ArrayList<>();

        for (HeroTranslationViewModel heroTranslationViewModel : heroTranslationViewModels) {
            heroTranslations.add(
                    new HeroTranslation(
                            heroTranslationViewModel.getHeroCode(),
                            heroTranslationViewModel.getHeroName()
                    )
            );
        }

        return heroTranslations;
    }
}
