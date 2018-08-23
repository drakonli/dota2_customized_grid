package drakonli.dota2.hero_grid_customizer.ui.view.customization.table;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HeroNamesCustomizationTableView implements Initializable
{
    @FXML
    public TableView<HeroTranslationViewModel> tableView;
    @FXML
    public TableColumn<HeroTranslationViewModel, String> heroCustomizationColumn;

    private final HeroGridViewModel heroGridViewModel;

    public HeroNamesCustomizationTableView(
            HeroGridViewModel heroGridViewModel
    )
    {
        this.heroGridViewModel = heroGridViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.tableView.itemsProperty().bind(
                new SimpleListProperty<>(this.heroGridViewModel.getHeroTranslationsViewModels())
        );
        this.handleHeroNameColumnEdit();
    }

    private void handleHeroNameColumnEdit()
    {
        this.heroCustomizationColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<HeroTranslationViewModel, String> editEvent) -> {
                    final int currentRowNumber = editEvent.getTablePosition().getRow();

                    final HeroTranslationViewModel currentlyEditedViewModel =
                            editEvent.getTableView().getItems().get(currentRowNumber);

                    currentlyEditedViewModel.setHeroName(editEvent.getNewValue());
                }
        );
    }
}
