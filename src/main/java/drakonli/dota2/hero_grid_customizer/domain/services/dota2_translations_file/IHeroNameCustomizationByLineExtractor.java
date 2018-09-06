package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;

import java.util.Optional;

public interface IHeroNameCustomizationByLineExtractor
{
    Optional<HeroNameCustomization> extractByLine(String line);
}
