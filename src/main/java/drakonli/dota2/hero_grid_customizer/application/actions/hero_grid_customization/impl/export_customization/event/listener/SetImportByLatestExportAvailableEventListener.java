package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.listener;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.AfterExportHeroGridCustomizationIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.models.ImportHeroGridCustomizationByLatestExportVM;
import drakonli.dota2.hero_grid_customizer.application.services.ImportByLatestExportAvailabilityManagerInterface;
import org.springframework.context.ApplicationListener;

public class SetImportByLatestExportAvailableEventListener implements
        ApplicationListener<AfterExportHeroGridCustomizationIntoFileActionEvent>
{
    private final ImportByLatestExportAvailabilityManagerInterface restoreAvailabilityManager;
    private final ImportHeroGridCustomizationByLatestExportVM importHeroGridCustomizationByLatestExportVM;

    public SetImportByLatestExportAvailableEventListener(
            ImportByLatestExportAvailabilityManagerInterface restoreAvailabilityManager,
            ImportHeroGridCustomizationByLatestExportVM importHeroGridCustomizationByLatestExportVM
    )
    {
        this.restoreAvailabilityManager = restoreAvailabilityManager;
        this.importHeroGridCustomizationByLatestExportVM = importHeroGridCustomizationByLatestExportVM;
    }

    @Override
    public void onApplicationEvent(
            AfterExportHeroGridCustomizationIntoFileActionEvent afterExportHeroGridCustomizationIntoFileActionEvent
    )
    {
        this.importHeroGridCustomizationByLatestExportVM
                .setIsImportByLatestExportAvailable(this.restoreAvailabilityManager.isImportByLatestExportAvailable());
    }
}