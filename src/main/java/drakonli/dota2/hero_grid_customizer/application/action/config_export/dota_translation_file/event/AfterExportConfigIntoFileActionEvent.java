package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event;

import drakonli.dota2.hero_grid_customizer.application.action.event.AbstractHeroTranslationsAndFileAwareApplicationEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;

import java.io.File;
import java.util.List;

public class AfterExportConfigIntoFileActionEvent extends AbstractHeroTranslationsAndFileAwareApplicationEvent
{
    public AfterExportConfigIntoFileActionEvent(
            Object source, File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            HeroNamesGridCustomization heroNamesGridCustomization
    )
    {
        super(source, file, heroTranslationViewModels, heroNamesGridCustomization);
    }
}
