package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.import_customization.from_file;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.IImportHeroGridCustomizationFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridCustomizationVM;
import drakonli.jcomponents.file.chooser.FileChooserFactoryInterface;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.File;

public class ImportGridCustomizationFromFileMenuItemView
{
    @FXML
    public MenuItem importByFileMenuItem;

    private final FileChooserFactoryInterface                fileChooserFactory;
    private final NotificatorInterface                       notificator;
    private final IImportHeroGridCustomizationFromFileAction importHeroGridCustomizationFromFileAction;
    private final HeroGridCustomizationVM                    heroGridCustomizationVM;

    public ImportGridCustomizationFromFileMenuItemView(
            FileChooserFactoryInterface fileChooserFactory,
            NotificatorInterface notificator,
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
