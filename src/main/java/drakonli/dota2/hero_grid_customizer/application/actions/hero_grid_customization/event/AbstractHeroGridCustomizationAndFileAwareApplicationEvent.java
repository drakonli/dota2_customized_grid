package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.event;

import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import org.springframework.context.ApplicationEvent;

import java.io.File;
import java.util.List;

abstract public class AbstractHeroGridCustomizationAndFileAwareApplicationEvent extends ApplicationEvent
{
    private final File                          file;
    private final List<HeroNameCustomizationVM> heroNameCustomizationVMList;
    private final HeroGridCustomization         heroGridCustomization;

    public AbstractHeroGridCustomizationAndFileAwareApplicationEvent(
            Object source, File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    )
    {
        super(source);

        this.file = file;
        this.heroNameCustomizationVMList = heroNameCustomizationVMList;
        this.heroGridCustomization = heroGridCustomization;
    }

    public File getFile()
    {
        return this.file;
    }

    public List<HeroNameCustomizationVM> getHeroNameCustomizationVMList()
    {
        return this.heroNameCustomizationVMList;
    }

    public HeroGridCustomization getHeroNameCustomization()
    {
        return this.heroGridCustomization;
    }
}
