package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.listener;

import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.AfterExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.services.ImportByLatestExportAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ImportHeroGridCustomizationByLatestExportVM;
import org.springframework.context.ApplicationListener;

public class SetImportByLatestExportAvailableEventListener implements
        ApplicationListener<AfterExportConfigIntoFileActionEvent>
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
            AfterExportConfigIntoFileActionEvent afterExportConfigIntoFileActionEvent
    )
    {
        this.importHeroGridCustomizationByLatestExportVM
                .setIsImportByLatestExportAvailable(this.restoreAvailabilityManager.isImportByLatestExportAvailable());
    }
}
