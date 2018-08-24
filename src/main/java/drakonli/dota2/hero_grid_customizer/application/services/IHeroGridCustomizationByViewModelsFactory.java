package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.util.List;

public interface IHeroGridCustomizationByViewModelsFactory
{
    HeroGridCustomization create(List<HeroNameCustomizationVM> heroNameCustomizationVMList);
}
