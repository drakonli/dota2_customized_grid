package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;

public interface IHeroNameCustomizationByLineExtractor
{
    HeroNameCustomization extractByLine(String line);
}
