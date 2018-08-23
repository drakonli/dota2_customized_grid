package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.io.File;
import java.util.List;

public interface IExportConfigIntoFileActionEventPublisher
{
    public void publishBeforeExportEvent(
            File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            HeroGridCustomization heroGridCustomization
    );

    public void publishAfterExportEvent(
            File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            HeroGridCustomization heroGridCustomization
    );
}
