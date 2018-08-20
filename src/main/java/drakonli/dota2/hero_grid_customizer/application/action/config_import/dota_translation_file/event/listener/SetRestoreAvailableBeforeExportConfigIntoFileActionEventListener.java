package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.listener;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.event.BeforeExportConfigIntoFileActionEvent;
import drakonli.dota2.hero_grid_customizer.application.services.RestoreAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByLatestSaveViewModel;
import org.springframework.context.ApplicationListener;

public class SetRestoreAvailableBeforeExportConfigIntoFileActionEventListener implements
        ApplicationListener<BeforeExportConfigIntoFileActionEvent>
{
    private final RestoreAvailabilityManagerInterface restoreAvailabilityManager;
    private final ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;

    public SetRestoreAvailableBeforeExportConfigIntoFileActionEventListener(
            RestoreAvailabilityManagerInterface restoreAvailabilityManager,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel
    )
    {
        this.restoreAvailabilityManager = restoreAvailabilityManager;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
    }

    @Override
    public void onApplicationEvent(
            BeforeExportConfigIntoFileActionEvent beforeExportConfigIntoFileActionEvent
    )
    {
        this.exportImportHeroGridByLatestSaveViewModel
                .setIsRestoreAvailable(this.restoreAvailabilityManager.isRestoreAvailable());
    }
}
