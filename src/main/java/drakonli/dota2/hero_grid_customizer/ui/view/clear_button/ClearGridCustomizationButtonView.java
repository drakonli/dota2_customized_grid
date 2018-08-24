package drakonli.dota2.hero_grid_customizer.ui.view.clear_button;

import drakonli.dota2.hero_grid_customizer.application.models.ExportImportHeroGridCustomizationByFileVM;
import drakonli.dota2.hero_grid_customizer.application.models.HeroGridCustomizationVM;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClearGridCustomizationButtonView
{
    @FXML
    public Button clearButton;

    private final HeroGridCustomizationVM heroGridCustomizationVM;
    private final ExportImportHeroGridCustomizationByFileVM exportImportHeroGridCustomizationByFileVM;
    private final NotificatorInterface notificator;

    public ClearGridCustomizationButtonView(
            HeroGridCustomizationVM heroGridCustomizationVM,
            ExportImportHeroGridCustomizationByFileVM exportImportHeroGridCustomizationByFileVM,
            NotificatorInterface notificator
    )
    {
        this.heroGridCustomizationVM = heroGridCustomizationVM;
        this.exportImportHeroGridCustomizationByFileVM = exportImportHeroGridCustomizationByFileVM;
        this.notificator = notificator;
    }

    public void onClearClick(ActionEvent actionEvent)
    {
        if (this.heroGridCustomizationVM.getHeroNameCustomizationVMList().isEmpty()) {
            this.notificator.success("The Hero Grid is already empty");

            return;
        }

        this.heroGridCustomizationVM.getHeroNameCustomizationVMList().clear();
        this.exportImportHeroGridCustomizationByFileVM.setChosenHeroGridFile(null);

        this.notificator.success("The Hero Grid has been cleared");
    }
}
