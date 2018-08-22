package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.ArrayList;
import java.util.List;

public class HeroTranslationViewModelsToDomainModelMapper
{
    public List<HeroNameCustomization> mapToNewEntityList(List<HeroTranslationViewModel> heroTranslationViewModels)
    {
        List<HeroNameCustomization> heroNameCustomizations = new ArrayList<>();

        for (HeroTranslationViewModel heroTranslationViewModel : heroTranslationViewModels) {
            heroNameCustomizations.add(
                    new HeroNameCustomization(
                            heroTranslationViewModel.getHeroCode(),
                            heroTranslationViewModel.getHeroName()
                    )
            );
        }

        return heroNameCustomizations;
    }
}
