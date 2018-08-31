package drakonli.dota2.hero_grid_customizer.ui.views.menu_bar.export_customization.into_current_file;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IExportHeroGridCustomizationIntoFileAction;
import drakonli.dota2.hero_grid_customizer.application.models.ExportImportHeroGridCustomizationByFileVM;
import drakonli.dota2.hero_grid_customizer.application.models.HeroGridCustomizationVM;
import drakonli.jcomponents.INotificator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ExportGridCustomizationIntoCurrentFileButtonView implements Initializable
{
    @FXML
    public MenuItem exportCustomizationIntoCurrentFileMenuItem;

    private final INotificator                               notificator;
    private final IExportHeroGridCustomizationIntoFileAction exportHeroGridCustomizationIntoFileAction;
    private final ExportImportHeroGridCustomizationByFileVM  exportImportHeroGridCustomizationByFileVM;
    private final HeroGridCustomizationVM                    heroGridCustomizationVM;

    public ExportGridCustomizationIntoCurrentFileButtonView(
            INotificator notificator,
            IExportHeroGridCustomizationIntoFileAction exportHeroGridCustomizationIntoFileAction,
            ExportImportHeroGridCustomizationByFileVM exportImportHeroGridCustomizationByFileVM,
            HeroGridCustomizationVM heroGridCustomizationVM
    )
    {
        this.notificator = notificator;
        this.exportHeroGridCustomizationIntoFileAction = exportHeroGridCustomizationIntoFileAction;
        this.exportImportHeroGridCustomizationByFileVM = exportImportHeroGridCustomizationByFileVM;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.exportCustomizationIntoCurrentFileMenuItem.visibleProperty().bind(
                this.exportImportHeroGridCustomizationByFileVM.getChosenHeroGridFileAvailableProperty()
        );
    }

    public void onExportClick(ActionEvent actionEvent)
    {
        try {
            File file = this.exportImportHeroGridCustomizationByFileVM
                    .getOptionalChosenHeroGridFile()
                    .orElseThrow(() -> new NullPointerException("No File was chosen"));

            this.exportHeroGridCustomizationIntoFileAction.exportCustomization(
                    file,
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Export success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
