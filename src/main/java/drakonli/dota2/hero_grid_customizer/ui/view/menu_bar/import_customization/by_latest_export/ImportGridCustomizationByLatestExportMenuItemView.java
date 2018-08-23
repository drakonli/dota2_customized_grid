package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.import_customization.by_latest_export;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save.IImportConfigByLatestSaveAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ImportHeroGridCustomizationByLatestExportVM;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportGridCustomizationByLatestExportMenuItemView implements Initializable
{
    @FXML
    public MenuItem importByLatestExportMenuItem;

    private NotificatorInterface notificator;
    private ImportHeroGridCustomizationByLatestExportVM importHeroGridCustomizationByLatestExportVM;
    private HeroGridCustomizationVM heroGridCustomizationVM;
    private IImportConfigByLatestSaveAction importConfigByLatestSaveAction;

    public ImportGridCustomizationByLatestExportMenuItemView(
            NotificatorInterface notificator,
            ImportHeroGridCustomizationByLatestExportVM importHeroGridCustomizationByLatestExportVM,
            HeroGridCustomizationVM heroGridCustomizationVM,
            IImportConfigByLatestSaveAction importConfigByLatestSaveAction
    )
    {
        this.notificator = notificator;
        this.importHeroGridCustomizationByLatestExportVM = importHeroGridCustomizationByLatestExportVM;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
        this.importConfigByLatestSaveAction = importConfigByLatestSaveAction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.importByLatestExportMenuItem.visibleProperty().bind(
                this.importHeroGridCustomizationByLatestExportVM.getImportByLatestExportAvailableProperty()
        );
    }

    public void onImportClick(ActionEvent actionEvent)
    {
        try {
            this.importConfigByLatestSaveAction.importConfig(
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Import by latest save success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
