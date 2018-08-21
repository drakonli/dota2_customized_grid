package drakonli.dota2.hero_grid_customizer.ui.save;

import drakonli.dota2.hero_grid_customizer.application.action.config_export.dota_translation_file.IExportConfigIntoFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveHeroNamesIntoFileButtonView implements Initializable
{
    @FXML
    public Button saveButton;

    private final NotificatorInterface notificator;
    private final IExportConfigIntoFileAction exportConfigIntoFileAction;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final HeroGridViewModel heroGridViewModel;

    public SaveHeroNamesIntoFileButtonView(
            NotificatorInterface notificator,
            IExportConfigIntoFileAction exportConfigIntoFileAction,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            HeroGridViewModel heroGridViewModel
    )
    {
        this.notificator = notificator;
        this.exportConfigIntoFileAction = exportConfigIntoFileAction;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.heroGridViewModel = heroGridViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.saveButton.visibleProperty().bind(
                this.exportImportHeroGridByFileViewModel.getChosenHeroGridFileAvailableProperty()
        );

        this.saveButton.managedProperty().bind(this.saveButton.visibleProperty());
    }

    public void onSaveClick(ActionEvent actionEvent)
    {
        try {
            File file = this.exportImportHeroGridByFileViewModel
                    .getOptionalChosenHeroGridFile()
                    .orElseThrow(() -> new NullPointerException("No File was chosen"));

            this.exportConfigIntoFileAction.exportConfig(file, this.heroGridViewModel.getHeroTranslationsViewModels());

            this.notificator.success("Save success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
