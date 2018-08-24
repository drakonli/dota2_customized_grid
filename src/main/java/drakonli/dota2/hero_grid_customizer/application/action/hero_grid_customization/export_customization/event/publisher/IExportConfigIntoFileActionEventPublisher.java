package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.io.File;
import java.util.List;

public interface IExportConfigIntoFileActionEventPublisher
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
