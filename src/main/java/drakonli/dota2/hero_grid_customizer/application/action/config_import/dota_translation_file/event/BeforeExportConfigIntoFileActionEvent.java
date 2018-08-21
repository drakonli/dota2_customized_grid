package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event;

import drakonli.dota2.hero_grid_customizer.application.action.event.AbstractHeroTranslationsAndFileAwareApplicationEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.File;
import java.util.List;

public class BeforeExportConfigIntoFileActionEvent extends AbstractHeroTranslationsAndFileAwareApplicationEvent
{
    public BeforeExportConfigIntoFileActionEvent(
            Object source, File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            List<HeroTranslation> heroTranslations
    )
    {
        super(source, file, heroTranslationViewModels, heroTranslations);
    }
}
