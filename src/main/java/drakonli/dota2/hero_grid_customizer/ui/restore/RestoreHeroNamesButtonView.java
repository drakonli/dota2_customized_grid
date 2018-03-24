package drakonli.dota2.hero_grid_customizer.ui.restore;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_handler.restore.RestoreButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.ui.handler.HideNodeOnObvservableListEmptyHandler;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RestoreHeroNamesButtonView implements Initializable
{
    @FXML
    public Button restoreButton;

    private HeroGridViewModel heroGridViewModel;
    private NotificatorInterface notificator;
    private List<RestoreButtonHandlerInterface> restoreButtonHandlers;

    public RestoreHeroNamesButtonView(
            HeroGridViewModel heroGridViewModel,
            NotificatorInterface notificator,
            List<RestoreButtonHandlerInterface> restoreButtonHandlers
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.notificator = notificator;
        this.restoreButtonHandlers = restoreButtonHandlers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        new HideNodeOnObvservableListEmptyHandler(
                this.heroGridViewModel.getHeroTranslations(),
                this.restoreButton
        ).handle();
    }

    public void onRestoreClick(ActionEvent actionEvent)
    {
        try {
            for (RestoreButtonHandlerInterface handler : restoreButtonHandlers) {
                handler.handle();
            }

            notificator.success("Restore success!");
        } catch (HandlerException e) {
            notificator.error(e.getCause().getMessage());
        }
    }
}
