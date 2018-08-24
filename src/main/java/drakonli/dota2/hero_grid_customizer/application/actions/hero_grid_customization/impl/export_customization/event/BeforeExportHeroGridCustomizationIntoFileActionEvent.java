package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.event.AbstractHeroGridCustomizationAndFileAwareApplicationEvent;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

import java.io.File;
import java.util.List;

public class BeforeExportHeroGridCustomizationIntoFileActionEvent
        extends AbstractHeroGridCustomizationAndFileAwareApplicationEvent
{
    public BeforeExportHeroGridCustomizationIntoFileActionEvent(
            Object source, File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    )
    {
        super(source, file, heroNameCustomizationVMList, heroGridCustomization);
    }
}
