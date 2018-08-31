package drakonli.dota2.hero_grid_customizer.ui.views.menu_bar.import_customization.from_file;

import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.models.HeroGridCustomizationVM;
import drakonli.jcomponents.file.chooser.IFileChooserFactory;
import drakonli.jcomponents.notificator.INotificator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.File;

public class ImportGridCustomizationFromFileMenuItemView
{
    @FXML
    public MenuItem importByFileMenuItem;

    private final IFileChooserFactory                        fileChooserFactory;
    private final INotificator                               notificator;
    private final IImportHeroGridCustomizationFromFileAction importHeroGridCustomizationFromFileAction;
    private final HeroGridCustomizationVM                    heroGridCustomizationVM;

    public ImportGridCustomizationFromFileMenuItemView(
            IFileChooserFactory fileChooserFactory,
            INotificator notificator,
            IImportHeroGridCustomizationFromFileAction importHeroGridCustomizationFromFileAction,
            HeroGridCustomizationVM heroGridCustomizationVM
    )
    {
        this.fileChooserFactory = fileChooserFactory;
        this.notificator = notificator;
        this.importHeroGridCustomizationFromFileAction = importHeroGridCustomizationFromFileAction;
        this.heroGridCustomizationVM = heroGridCustomizationVM;
    }

    public void onImportClick(ActionEvent actionEvent)
    {
        File file = this.fileChooserFactory.createFileChooser().showOpenDialog(
                this.importByFileMenuItem.getParentPopup().getScene().getWindow()
        );

        if (null == file) {
            return;
        }

        try {
            this.importHeroGridCustomizationFromFileAction.importCustomization(
                    file,
                    this.heroGridCustomizationVM.getHeroNameCustomizationVMList()
            );

            this.notificator.success("Import by file success!");
        } catch (Exception e) {
            this.notificator.error(e.getCause().getMessage());
        }
    }
}
