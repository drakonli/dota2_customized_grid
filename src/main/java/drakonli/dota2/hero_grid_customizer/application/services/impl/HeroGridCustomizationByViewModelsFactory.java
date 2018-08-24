package drakonli.dota2.hero_grid_customizer.application.services.impl;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.IHeroGridCustomizationByViewModelsFactory;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;

import java.util.List;

public class HeroGridCustomizationByViewModelsFactory implements IHeroGridCustomizationByViewModelsFactory
{
    @Override
    public HeroGridCustomization create(List<HeroNameCustomizationVM> heroNameCustomizationVMList)
    {
        HeroGridCustomization heroGridCustomization = new HeroGridCustomization();

        for (HeroNameCustomizationVM heroNameCustomizationVM : heroNameCustomizationVMList) {
            heroGridCustomization.add(
                    new HeroNameCustomization(
                            heroNameCustomizationVM.getHeroCode(),
                            heroNameCustomizationVM.getHeroName()
                    )
            );
        }

        return heroGridCustomization;
    }
}
