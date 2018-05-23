package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.services.RestoreAvailabilityManagerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.latest.ExportImportHeroGridByLatestSaveViewModel;

public class RestoreAvailabilityHandler implements SaveButtonHandlerInterface
{
    private final RestoreAvailabilityManagerInterface restoreAvailabilityManager;
    private final ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;

    public RestoreAvailabilityHandler(
            RestoreAvailabilityManagerInterface restoreAvailabilityManager,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel
    )
    {
        this.restoreAvailabilityManager = restoreAvailabilityManager;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
    }

    @Override
    public void handle()
    {
        this.exportImportHeroGridByLatestSaveViewModel
                .setIsRestoreAvailable(this.restoreAvailabilityManager.isRestoreAvailable());
    }
}
