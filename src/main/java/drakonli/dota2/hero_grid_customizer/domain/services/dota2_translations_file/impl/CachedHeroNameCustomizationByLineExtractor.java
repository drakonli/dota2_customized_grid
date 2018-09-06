package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CachedHeroNameCustomizationByLineExtractor implements IHeroNameCustomizationByLineExtractor
{
    private final Map<String, Optional<HeroNameCustomization>> lineToCustomizationCache = new HashMap<>();

    private final IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor;

    public CachedHeroNameCustomizationByLineExtractor(
            IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor
    )
    {
        this.heroNameCustomizationByLineExtractor = heroNameCustomizationByLineExtractor;
    }

    @Override
    public Optional<HeroNameCustomization> extractByLine(String line)
    {
        if (this.lineToCustomizationCache.containsKey(line)) {
            return this.lineToCustomizationCache.get(line);
        }

        Optional<HeroNameCustomization> optionalHeroNameCustomization =
                this.heroNameCustomizationByLineExtractor.extractByLine(line);

        if (optionalHeroNameCustomization.isPresent()) {
            this.lineToCustomizationCache.put(line, optionalHeroNameCustomization);
        }

        return optionalHeroNameCustomization;
    }
}
