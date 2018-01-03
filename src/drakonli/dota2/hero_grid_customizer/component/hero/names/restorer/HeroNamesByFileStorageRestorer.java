package drakonli.dota2.hero_grid_customizer.component.hero.names.restorer;

import drakonli.dota2.hero_grid_customizer.component.hero.names.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.io.IOException;
import java.util.List;

public class HeroNamesByFileStorageRestorer
{
    private final HeroNamesByFileStorage storage;

    public HeroNamesByFileStorageRestorer(HeroNamesByFileStorage storage)
    {
        this.storage = storage;
    }

    public void restoreLatestHeroNames(List<HeroTranslationViewModel> heroTranslationViewModels)
            throws ClassNotFoundException, IOException, LastVersionOfHeroNamesIsEmptyException
    {
        List<HeroTranslation> heroNamesList = this.storage.getLatest();

        if (heroNamesList.isEmpty()) {
            throw new LastVersionOfHeroNamesIsEmptyException();
        }

        heroTranslationViewModels.clear();

        for (HeroTranslation heroTranslation : heroNamesList) {
            heroTranslationViewModels.add(
                    new HeroTranslationViewModel(heroTranslation.getHeroName(), heroTranslation.getHeroCode())
            );
        }
    }
}
