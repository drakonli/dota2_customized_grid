package drakonli.dota2.hero_grid_customizer.ui.views.menu_bar.import_customization.by_latest_export;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IImportHeroGridCustomizationByLatestSaveAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroGridCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.models.ImportHeroGridCustomizationByLatestExportVM;
import drakonli.jcomponents.INotificator;
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

    private INotificator                                   notificator;
    private ImportHeroGridCustomizationByLatestExportVM    importHeroGridCustomizationByLatestExportVM;
    private HeroGridCustomizationVM                        heroGridCustomizationVM;
    private IImportHeroGridCustomizationByLatestSaveAction importHeroGridCustomizationByLatestSaveAction;

    public ImportGridCustomizationByLatestExportMenuItemView(
            INotificator notificator,
            ImportHeroGridCustomizationByLatestExportVM importHeroGridCustomizationByLatestExportVM,
            HeroGridCustomizationVM heroGridCustomizationVM,
            IImportHeroGridCustomizationByLatestSaveAction importHeroGridCustomizationByLatestSaveAction
    )
    {
        this.notificator = notificator;
        this.importHeroGridCustomizationByLatestExportVM = importHeroGridCustomizationByLatestExportVM;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
        this.importHeroGridCustomizationByLatestSaveAction = importHeroGridCustomizationByLatestSaveAction;
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
            this.importHeroGridCustomizationByLatestSaveAction.importCustomization(
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Import by latest save success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
