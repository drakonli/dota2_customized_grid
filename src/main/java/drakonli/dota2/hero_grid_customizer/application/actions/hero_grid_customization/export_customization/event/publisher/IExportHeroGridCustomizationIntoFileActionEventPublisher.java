package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.export_customization.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

import java.io.File;
import java.util.List;

public interface IExportHeroGridCustomizationIntoFileActionEventPublisher
{
    public void publishBeforeExportEvent(
            File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    );

    public void publishAfterExportEvent(
            File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    );
}
