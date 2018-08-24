package drakonli.dota2.hero_grid_customizer.application.services.impl;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.IHeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;

import java.util.List;

public class HeroGridCustomizationToViewModelsMapper implements IHeroGridCustomizationToViewModelsMapper
{
    @Override
    public void map(
            HeroGridCustomization heroGridCustomization,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList
    )
    {
        heroNameCustomizationVMList.clear();

        for (HeroNameCustomization heroNameCustomization : heroGridCustomization) {
            heroNameCustomizationVMList.add(
                    new HeroNameCustomizationVM(
                            heroNameCustomization.getHeroName(),
                            heroNameCustomization.getHeroNameUID()
                    )
            );
        }
    }
}
