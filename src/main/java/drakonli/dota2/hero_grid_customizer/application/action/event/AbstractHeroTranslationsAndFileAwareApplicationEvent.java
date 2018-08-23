package drakonli.dota2.hero_grid_customizer.application.action.event;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import org.springframework.context.ApplicationEvent;

import java.io.File;
import java.util.List;

abstract public class AbstractHeroTranslationsAndFileAwareApplicationEvent extends ApplicationEvent
{
    private File file;
    private List<HeroTranslationViewModel> heroTranslationViewModels;
    private HeroGridCustomization heroGridCustomization;

    public AbstractHeroTranslationsAndFileAwareApplicationEvent(
            Object source, File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            HeroGridCustomization heroGridCustomization
    )
    {
        super(source);

        this.file = file;
        this.heroTranslationViewModels = heroTranslationViewModels;
        this.heroGridCustomization = heroGridCustomization;
    }

    public File getFile()
    {
        return file;
    }

    public List<HeroTranslationViewModel> getHeroTranslationViewModels()
    {
        return heroTranslationViewModels;
    }

    public HeroGridCustomization getHeroNameCustomizations()
    {
        return this.heroGridCustomization;
    }
}
