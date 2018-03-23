package drakonli.dota2.hero_grid_customizer.ui.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_handler.save.SaveButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.collections.ListChangeListener;
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

    private final HeroGridViewModel heroGridViewModel;
    private final NotificatorInterface notificator;
    private final List<SaveButtonHandlerInterface> saveButtonHandlers;

    public SaveHeroNamesIntoFileButtonView(
            HeroGridViewModel heroGridViewModel,
            NotificatorInterface notificator,
            List<SaveButtonHandlerInterface> saveButtonHandlers
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.notificator = notificator;
        this.saveButtonHandlers = saveButtonHandlers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.changeSaveButtonManagementOnHeroCollectionChange();
    }

    private void changeSaveButtonManagementOnHeroCollectionChange()
    {
        this.saveButton.setManaged(false);

        this.heroGridViewModel.getHeroTranslations().addListener(
            (ListChangeListener.Change<? extends HeroTranslationViewModel > change)
                    -> this.saveButton.setManaged(!change.getList().isEmpty())
        );
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
