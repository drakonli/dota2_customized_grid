package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event;

import drakonli.dota2.hero_grid_customizer.application.action.event.AbstractHeroTranslationsAndFileAwareApplicationEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.io.File;
import java.util.List;

public class BeforeExportConfigIntoFileActionEvent extends AbstractHeroTranslationsAndFileAwareApplicationEvent
{
    public BeforeExportConfigIntoFileActionEvent(
            Object source, File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            List<HeroNameCustomization> heroNameCustomizations
    )
    {
        super(source, file, heroTranslationViewModels, heroNameCustomizations);
    }
}
