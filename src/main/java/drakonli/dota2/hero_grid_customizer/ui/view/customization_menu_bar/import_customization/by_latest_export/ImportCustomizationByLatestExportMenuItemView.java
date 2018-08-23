package drakonli.dota2.hero_grid_customizer.ui.view.customization_menu_bar.import_customization.by_latest_export;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save.IImportConfigByLatestSaveAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByLatestSaveViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportCustomizationByLatestExportMenuItemView implements Initializable
{
    @FXML
    public MenuItem importByLatestExportMenuItem;

    private NotificatorInterface notificator;
    private ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;
    private HeroGridViewModel heroGridViewModel;
    private IImportConfigByLatestSaveAction importConfigByLatestSaveAction;

    public ImportCustomizationByLatestExportMenuItemView(
            NotificatorInterface notificator,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel,
            HeroGridViewModel heroGridViewModel,
            IImportConfigByLatestSaveAction importConfigByLatestSaveAction
    )
    {
        this.notificator = notificator;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
        this.heroGridViewModel = heroGridViewModel;
        this.importConfigByLatestSaveAction = importConfigByLatestSaveAction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.importByLatestExportMenuItem.visibleProperty().bind(
                this.exportImportHeroGridByLatestSaveViewModel.getRestoreAvailableProperty()
        );
    }

    public void onImportClick(ActionEvent actionEvent)
    {
        try {
            this.importConfigByLatestSaveAction.importConfig(this.heroGridViewModel.getHeroTranslationsViewModels());

            this.notificator.success("Import by latest save success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
