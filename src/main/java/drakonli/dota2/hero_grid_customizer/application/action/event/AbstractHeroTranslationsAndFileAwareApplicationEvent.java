package drakonli.dota2.hero_grid_customizer.application.action.event;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import org.springframework.context.ApplicationEvent;

import java.io.File;
import java.util.List;

abstract public class AbstractHeroTranslationsAndFileAwareApplicationEvent extends ApplicationEvent
{
    private File file;
    private List<HeroTranslationViewModel> heroTranslationViewModels;
    private HeroNamesGridCustomization heroNamesGridCustomization;

    public AbstractHeroTranslationsAndFileAwareApplicationEvent(
            Object source, File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            HeroNamesGridCustomization heroNamesGridCustomization
    )
    {
        super(source);

        this.file = file;
        this.heroTranslationViewModels = heroTranslationViewModels;
        this.heroNamesGridCustomization = heroNamesGridCustomization;
    }

    public File getFile()
    {
        return file;
    }

    public List<HeroTranslationViewModel> getHeroTranslationViewModels()
    {
        return heroTranslationViewModels;
    }

    public HeroNamesGridCustomization getHeroNameCustomizations()
    {
        return this.heroNamesGridCustomization;
    }
}
