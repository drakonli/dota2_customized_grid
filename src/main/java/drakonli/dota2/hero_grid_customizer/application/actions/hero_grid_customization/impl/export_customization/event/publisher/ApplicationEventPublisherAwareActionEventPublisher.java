package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.publisher;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.AfterExportHeroGridCustomizationIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.BeforeExportHeroGridCustomizationIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.io.File;
import java.util.List;

public class ApplicationEventPublisherAwareActionEventPublisher implements
        IExportHeroGridCustomizationIntoFileActionEventPublisher,
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
        BeforeExportHeroGridCustomizationIntoFileActionEvent event =
                new BeforeExportHeroGridCustomizationIntoFileActionEvent(
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
        AfterExportHeroGridCustomizationIntoFileActionEvent event =
                new AfterExportHeroGridCustomizationIntoFileActionEvent(
                        this,
                        file,
                        heroNameCustomizationVMList,
                        heroGridCustomization
                );

        this.applicationEventPublisher.publishEvent(event);
    }
}
