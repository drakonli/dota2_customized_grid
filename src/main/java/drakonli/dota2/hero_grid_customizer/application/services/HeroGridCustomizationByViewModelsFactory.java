package drakonli.dota2.hero_grid_customizer.application.services;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

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
