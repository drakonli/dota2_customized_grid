package drakonli.dota2.hero_grid_customizer.application.view_model.services;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class HeroGridCustomizationToViewModelsMapper
{
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
