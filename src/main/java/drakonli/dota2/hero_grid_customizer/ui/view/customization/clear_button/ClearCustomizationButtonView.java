package drakonli.dota2.hero_grid_customizer.ui.view.customization.clear_button;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClearCustomizationButtonView
{
    @FXML
    public Button clearButton;

    private final HeroGridViewModel heroGridViewModel;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final NotificatorInterface notificator;

    public ClearCustomizationButtonView(
            HeroGridViewModel heroGridViewModel,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            NotificatorInterface notificator
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.notificator = notificator;
    }

    public void onClearClick(ActionEvent actionEvent)
    {
        if (this.heroGridViewModel.getHeroTranslationsViewModels().isEmpty()) {
            this.notificator.success("The Hero Grid is already empty");

            return;
        }

        this.heroGridViewModel.getHeroTranslationsViewModels().clear();
        this.exportImportHeroGridByFileViewModel.setChosenHeroGridFile(null);

        this.notificator.success("The Hero Grid has been cleared");
    }
}
