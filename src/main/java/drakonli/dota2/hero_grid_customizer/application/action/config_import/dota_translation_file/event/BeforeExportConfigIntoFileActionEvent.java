package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import org.springframework.context.ApplicationEvent;

import java.io.File;
import java.util.List;

public class BeforeExportConfigIntoFileActionEvent extends ApplicationEvent
{
    private File file;
    private List<HeroTranslationViewModel> heroTranslationViewModels;
    private List<HeroTranslation> heroTranslations;

    public BeforeExportConfigIntoFileActionEvent(
            Object source,
            File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            List<HeroTranslation> heroTranslations
    )
    {
        super(source);

        this.file = file;
        this.heroTranslationViewModels = heroTranslationViewModels;
        this.heroTranslations = heroTranslations;
    }

    public File getFile()
    {
        return file;
    }

    public List<HeroTranslationViewModel> getHeroTranslationViewModels()
    {
        return heroTranslationViewModels;
    }

    public List<HeroTranslation> getHeroTranslations()
    {
        return heroTranslations;
    }
}
