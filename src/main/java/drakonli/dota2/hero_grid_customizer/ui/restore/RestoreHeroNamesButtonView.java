package drakonli.dota2.hero_grid_customizer.ui.restore;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save.IImportConfigByLatestSaveAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByFileViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.ExportImportHeroGridByLatestSaveViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class RestoreHeroNamesButtonView implements Initializable
{
    @FXML
    public MenuItem restoreButton;

    private NotificatorInterface notificator;
    private ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel;
    private ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private HeroGridViewModel heroGridViewModel;
    private IImportConfigByLatestSaveAction importConfigByLatestSaveAction;

    public RestoreHeroNamesButtonView(
            NotificatorInterface notificator,
            ExportImportHeroGridByLatestSaveViewModel exportImportHeroGridByLatestSaveViewModel,
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            HeroGridViewModel heroGridViewModel,
            IImportConfigByLatestSaveAction importConfigByLatestSaveAction
    )
    {
        this.notificator = notificator;
        this.exportImportHeroGridByLatestSaveViewModel = exportImportHeroGridByLatestSaveViewModel;
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.heroGridViewModel = heroGridViewModel;
        this.importConfigByLatestSaveAction = importConfigByLatestSaveAction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.restoreButton.visibleProperty().bind(
                Bindings.and(
                        this.exportImportHeroGridByLatestSaveViewModel.getRestoreAvailableProperty(),
                        this.exportImportHeroGridByFileViewModel.getChosenHeroGridFileAvailableProperty()
                )
        );
    }

    public void onRestoreClick(ActionEvent actionEvent)
    {
        try {
            this.importConfigByLatestSaveAction.importConfig(this.heroGridViewModel.getHeroTranslationsViewModels());

            this.notificator.success("Restore success!");
        } catch (Exception e) {
            this.notificator.error(e.getMessage());
        }
    }
}
