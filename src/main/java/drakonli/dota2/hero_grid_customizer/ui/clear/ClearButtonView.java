package drakonli.dota2.hero_grid_customizer.ui.clear;

import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClearButtonView
{
    @FXML
    public Button clearButton;

    private final HeroGridViewModel heroGridViewModel;
    private final NotificatorInterface notificator;

    public ClearButtonView(
            HeroGridViewModel heroGridViewModel,
            NotificatorInterface notificator
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.notificator = notificator;
    }

    public void onClearClick(ActionEvent actionEvent)
    {
        if (this.heroGridViewModel.getHeroTranslations().isEmpty()) {
            this.notificator.success("The Hero Grid is already empty");

            return;
        }

        this.heroGridViewModel.getHeroTranslations().clear();

        this.notificator.success("The Hero Grid has been cleared");
    }
}
