package drakonli.dota2.hero_grid_customizer.ui.views.menu_bar.export_customization.into_file;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IExportHeroGridCustomizationIntoFileAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroGridCustomizationVM;
import drakonli.jcomponents.file.chooser.IFileChooserFactory;
import drakonli.jcomponents.notificator.INotificator;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ExportGridCustomizationIntoFileButtonView implements Initializable
{
    @FXML
    public MenuItem exportCustomizationIntoFileMenuItem;

    private final INotificator                               notificator;
    private final IExportHeroGridCustomizationIntoFileAction exportHeroGridCustomizationIntoFileAction;
    private final HeroGridCustomizationVM                    heroGridCustomizationVM;
    private final IFileChooserFactory                        fileChooserFactory;

    public ExportGridCustomizationIntoFileButtonView(
            INotificator notificator,
            IExportHeroGridCustomizationIntoFileAction exportHeroGridCustomizationIntoFileAction,
            HeroGridCustomizationVM heroGridCustomizationVM,
            IFileChooserFactory fileChooserFactory
    )
    {
        this.notificator = notificator;
        this.exportHeroGridCustomizationIntoFileAction = exportHeroGridCustomizationIntoFileAction;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
        this.fileChooserFactory = fileChooserFactory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.exportCustomizationIntoFileMenuItem.visibleProperty().bind(
                Bindings
                        .size(this.heroGridCustomizationVM.getHeroNameCustomizationVMList())
                        .isNotEqualTo(0)
        );
    }

    public void onExportClick(ActionEvent actionEvent)
    {
        try {
            File file = this.fileChooserFactory.createFileChooser().showOpenDialog(
                    this.exportCustomizationIntoFileMenuItem.getParentPopup().getScene().getWindow()
            );

            if (null == file) {
                return;
            }

            this.exportHeroGridCustomizationIntoFileAction.exportCustomization(
                    file,
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Export success!");
        }
        catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
