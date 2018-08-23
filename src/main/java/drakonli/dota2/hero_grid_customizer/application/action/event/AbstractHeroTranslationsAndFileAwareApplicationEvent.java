package drakonli.dota2.hero_grid_customizer.application.action.event;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import org.springframework.context.ApplicationEvent;

import java.io.File;
import java.util.List;

abstract public class AbstractHeroTranslationsAndFileAwareApplicationEvent extends ApplicationEvent
{
    private File file;
    private List<HeroNameCustomizationVM> heroNameCustomizationVMList;
    private HeroGridCustomization heroGridCustomization;

    public AbstractHeroTranslationsAndFileAwareApplicationEvent(
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
        return file;
    }

    public List<HeroNameCustomizationVM> getHeroNameCustomizationVMList()
    {
        return heroNameCustomizationVMList;
    }

    public HeroGridCustomization getHeroNameCustomization()
    {
        return this.heroGridCustomization;
    }
}
