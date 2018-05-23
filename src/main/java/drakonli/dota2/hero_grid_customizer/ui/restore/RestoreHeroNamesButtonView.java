package drakonli.dota2.hero_grid_customizer.ui.restore;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_handler.restore.RestoreButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.latest.ExportImportHeroGridByLatestSaveViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RestoreHeroNamesButtonView implements Initializable
{
    @FXML
    public MenuItem restoreButton;

    private NotificatorInterface notificator;
    private List<RestoreButtonHandlerInterface> restoreButtonHandlers;
    private ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;

    public RestoreHeroNamesButtonView(
            NotificatorInterface notificator,
            List<RestoreButtonHandlerInterface> restoreButtonHandlers,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel
    )
    {
        this.notificator = notificator;
        this.restoreButtonHandlers = restoreButtonHandlers;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.restoreButton.visibleProperty().bind(
                this.exportImportHeroGridByLatestSaveViewModel.getRestoreAvailableProperty()
        );
    }

    public void onRestoreClick(ActionEvent actionEvent)
    {
        try {
            for (RestoreButtonHandlerInterface handler : restoreButtonHandlers) {
                handler.handle();
            }

            this.notificator.success("Restore success!");
        } catch (HandlerException e) {
            this.notificator.error(e.getCause().getMessage());
        }
    }
}
