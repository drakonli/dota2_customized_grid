package drakonli.dota2.hero_grid_customizer.view_model.hero.translation;

import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.util.List;

public class HeroTranslationViewModelsToEntityMapper
{
    public void map(
            List<HeroTranslationViewModel> heroTranslationViewModels,
            List<HeroTranslation> heroTranslations
    )
    {
        for (HeroTranslationViewModel heroTranslationViewModel : heroTranslationViewModels) {
            heroTranslations.add(
                    new HeroTranslation(
                            heroTranslationViewModel.getHeroCode(),
                            heroTranslationViewModel.getHeroName()
                    )
            );
        }
    }
}
