package drakonli.dota2.hero_grid_customizer.ui.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_handler.save.SaveButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SaveHeroNamesIntoFileButtonView implements Initializable
{
    @FXML
    public Button saveButton;

    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final NotificatorInterface notificator;
    private final List<SaveButtonHandlerInterface> saveButtonHandlers;

    public SaveHeroNamesIntoFileButtonView(
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            NotificatorInterface notificator,
            List<SaveButtonHandlerInterface> saveButtonHandlers
    )
    {
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.notificator = notificator;
        this.saveButtonHandlers = saveButtonHandlers;
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
            for (SaveButtonHandlerInterface handler : this.saveButtonHandlers) {
                handler.handle();
            }

            this.notificator.success("Save success!");
        } catch (HandlerException e) {
            this.notificator.error(e.getCause().getMessage());
        }
    }
}
