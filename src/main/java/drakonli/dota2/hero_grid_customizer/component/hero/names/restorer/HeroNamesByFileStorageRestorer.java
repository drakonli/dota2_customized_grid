package drakonli.dota2.hero_grid_customizer.component.hero.names.restorer;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.io.IOException;
import java.util.List;

public class HeroNamesByFileStorageRestorer
{
    private final HeroNamesByFileStorage storage;

    public HeroNamesByFileStorageRestorer(HeroNamesByFileStorage storage)
    {
        this.storage = storage;
    }

    public void restoreLatestHeroNames(List<HeroTranslation> heroTranslations)
            throws ClassNotFoundException, IOException, LastVersionOfHeroNamesIsEmptyException
    {
        List<HeroTranslation> storedHeroTranslations = this.storage.getLatest();

        if (storedHeroTranslations.isEmpty()) {
            throw new LastVersionOfHeroNamesIsEmptyException();
        }

        heroTranslations.clear();

        for (HeroTranslation heroTranslation : storedHeroTranslations) {
            heroTranslations.add(
                    new HeroTranslation(heroTranslation.getHeroCode(), heroTranslation.getHeroName())
            );
        }
    }
}
