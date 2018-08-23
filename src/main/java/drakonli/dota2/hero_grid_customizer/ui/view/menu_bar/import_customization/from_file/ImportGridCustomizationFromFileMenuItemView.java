package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.import_customization.from_file;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.IImportConfigFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
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

    private final FileChooserFactoryInterface fileChooserFactory;
    private final NotificatorInterface notificator;
    private final IImportConfigFromFileAction importConfigFromFileAction;
    private final HeroGridViewModel heroGridViewModel;

    public ImportGridCustomizationFromFileMenuItemView(
            FileChooserFactoryInterface fileChooserFactory,
            NotificatorInterface notificator,
            IImportConfigFromFileAction importConfigFromFileAction,
            HeroGridViewModel heroGridViewModel
    )
    {
        this.fileChooserFactory = fileChooserFactory;
        this.notificator = notificator;
        this.importConfigFromFileAction = importConfigFromFileAction;
        this.heroGridViewModel = heroGridViewModel;
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
            this.importConfigFromFileAction.importConfig(file, this.heroGridViewModel.getHeroNameCustomizationVMList());

            this.notificator.success("Import by file success!");
        } catch (Exception e) {
            this.notificator.error(e.getCause().getMessage());
        }
    }
}
