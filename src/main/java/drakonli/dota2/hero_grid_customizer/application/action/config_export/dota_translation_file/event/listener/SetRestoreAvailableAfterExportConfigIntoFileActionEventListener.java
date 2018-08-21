package drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.listener;

import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.event.AfterExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.services.RestoreAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByLatestSaveViewModel;
import org.springframework.context.ApplicationListener;

public class SetRestoreAvailableAfterExportConfigIntoFileActionEventListener implements
        ApplicationListener<AfterExportConfigIntoFileActionEvent>
{
    private final RestoreAvailabilityManagerInterface restoreAvailabilityManager;
    private final ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;

    public SetRestoreAvailableAfterExportConfigIntoFileActionEventListener(
            RestoreAvailabilityManagerInterface restoreAvailabilityManager,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel
    )
    {
        this.restoreAvailabilityManager = restoreAvailabilityManager;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
    }

    @Override
    public void onApplicationEvent(
            AfterExportConfigIntoFileActionEvent afterExportConfigIntoFileActionEvent
    )
    {
        this.exportImportHeroGridByLatestSaveViewModel
                .setIsRestoreAvailable(this.restoreAvailabilityManager.isRestoreAvailable());
    }
}
