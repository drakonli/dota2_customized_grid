package drakonli.dota2.hero_grid_customizer.application.view_model.translation;

import drakonli.dota2.hero_grid_customizer.domain.entity.HeroTranslation;

import java.util.ArrayList;
import java.util.List;

public class HeroTranslationViewModelsToEntityMapper
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
