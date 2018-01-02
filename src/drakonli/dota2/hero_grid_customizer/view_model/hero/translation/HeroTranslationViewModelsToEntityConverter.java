package drakonli.dota2.hero_grid_customizer.view_model.hero.translation;

import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.util.ArrayList;
import java.util.List;

public class HeroTranslationViewModelsToEntityConverter
{
    public List<HeroTranslation> convert(List<HeroTranslationViewModel> heroTranslationViewModels)
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
