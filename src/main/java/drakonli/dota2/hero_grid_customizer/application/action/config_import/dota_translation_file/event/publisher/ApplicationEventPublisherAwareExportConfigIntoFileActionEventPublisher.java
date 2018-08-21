package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.AfterExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.BeforeExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.io.File;
import java.util.List;

public class ApplicationEventPublisherAwareExportConfigIntoFileActionEventPublisher implements
        IExportConfigIntoFileActionEventPublisher,
        ApplicationEventPublisherAware
{
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher
    )
    {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishBeforeExportEvent(
            File file,
            List<HeroTranslationViewModel> heroTranslationViewModels,
            List<HeroTranslation> heroTranslations
    )
    {
        BeforeExportConfigIntoFileActionEvent event =
                new BeforeExportConfigIntoFileActionEvent(this, file, heroTranslationViewModels, heroTranslations);

        this.applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishAfterExportEvent(
            File file, List<HeroTranslationViewModel> heroTranslationViewModels, List<HeroTranslation> heroTranslations
    )
    {
        AfterExportConfigIntoFileActionEvent event =
                new AfterExportConfigIntoFileActionEvent(this, file, heroTranslationViewModels, heroTranslations);

        this.applicationEventPublisher.publishEvent(event);
    }
}
