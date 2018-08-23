package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.export_customization.into_current_file;

import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.IExportConfigIntoFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridCustomizationVM;
import drakonli.jcomponents.notificator.NotificatorInterface;
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

    private final NotificatorInterface notificator;
    private final IExportConfigIntoFileAction exportConfigIntoFileAction;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final HeroGridCustomizationVM heroGridCustomizationVM;

    public ExportGridCustomizationIntoCurrentFileButtonView(
            NotificatorInterface notificator,
            IExportConfigIntoFileAction exportConfigIntoFileAction,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            HeroGridCustomizationVM heroGridCustomizationVM
    )
    {
        this.notificator = notificator;
        this.exportConfigIntoFileAction = exportConfigIntoFileAction;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.exportCustomizationIntoCurrentFileMenuItem.visibleProperty().bind(
                this.exportImportHeroGridByFileViewModel.getChosenHeroGridFileAvailableProperty()
        );
    }

    public void onExportClick(ActionEvent actionEvent)
    {
        try {
            File file = this.exportImportHeroGridByFileViewModel
                    .getOptionalChosenHeroGridFile()
                    .orElseThrow(() -> new NullPointerException("No File was chosen"));

            this.exportConfigIntoFileAction.exportConfig(
                    file,
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Export success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
