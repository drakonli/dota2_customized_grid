package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.listener;

import drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.export_customization.event.AfterExportHeroGridCustomizationIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.services.ImportByLatestExportAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ImportHeroGridCustomizationByLatestExportVM;
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
