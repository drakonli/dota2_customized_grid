package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.AfterExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.BeforeExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
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
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    )
    {
        BeforeExportConfigIntoFileActionEvent event =
                new BeforeExportConfigIntoFileActionEvent(
                        this,
                        file,
                        heroNameCustomizationVMList,
                        heroGridCustomization
                );

        this.applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishAfterExportEvent(
            File file,
            List<HeroNameCustomizationVM> heroNameCustomizationVMList,
            HeroGridCustomization heroGridCustomization
    )
    {
        AfterExportConfigIntoFileActionEvent event =
                new AfterExportConfigIntoFileActionEvent(
                        this,
                        file,
                        heroNameCustomizationVMList,
                        heroGridCustomization
                );

        this.applicationEventPublisher.publishEvent(event);
    }
}
