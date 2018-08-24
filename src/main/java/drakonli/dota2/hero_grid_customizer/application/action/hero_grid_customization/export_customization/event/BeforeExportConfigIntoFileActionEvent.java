package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event;

import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.event.AbstractHeroTranslationsAndFileAwareApplicationEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.io.File;
import java.util.List;

public class BeforeExportConfigIntoFileActionEvent extends AbstractHeroTranslationsAndFileAwareApplicationEvent
{
    public BeforeExportConfigIntoFileActionEvent(
            Object source, File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    )
    {
        super(source, file, heroNameCustomizationVMList, heroGridCustomization);
    }
}
