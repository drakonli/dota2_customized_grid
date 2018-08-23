package drakonli.dota2.hero_grid_customizer.ui.view.clear_button;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridCustomizationVM;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClearGridCustomizationButtonView
{
    @FXML
    public Button clearButton;

    private final HeroGridCustomizationVM heroGridCustomizationVM;
    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final NotificatorInterface notificator;

    public ClearGridCustomizationButtonView(
            HeroGridCustomizationVM heroGridCustomizationVM,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            NotificatorInterface notificator
    )
    {
        this.heroGridCustomizationVM = heroGridCustomizationVM;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.notificator = notificator;
    }

    public void onClearClick(ActionEvent actionEvent)
    {
        if (this.heroGridCustomizationVM.getHeroNameCustomizationVMList().isEmpty()) {
            this.notificator.success("The Hero Grid is already empty");

            return;
        }

        this.heroGridCustomizationVM.getHeroNameCustomizationVMList().clear();
        this.exportImportHeroGridByFileViewModel.setChosenHeroGridFile(null);

        this.notificator.success("The Hero Grid has been cleared");
    }
}
