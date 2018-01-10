package drakonli.dota2.hero_grid_customizer.view_model.hero.translation;

import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

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
