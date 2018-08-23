package drakonli.dota2.hero_grid_customizer.ui.view.table;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GridCustomizationTableView implements Initializable
{
    @FXML
    public TableView<HeroNameCustomizationVM> tableView;
    @FXML
    public TableColumn<HeroNameCustomizationVM, String> heroCustomizationColumn;

    private final HeroGridViewModel heroGridViewModel;

    public GridCustomizationTableView(
            HeroGridViewModel heroGridViewModel
    )
    {
        this.heroGridViewModel = heroGridViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.tableView.itemsProperty().bind(
                new SimpleListProperty<>(this.heroGridViewModel.getHeroNameCustomizationVMList())
        );
        this.handleHeroNameColumnEdit();
    }

    private void handleHeroNameColumnEdit()
    {
        this.heroCustomizationColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<HeroNameCustomizationVM, String> editEvent) -> {
                    final int currentRowNumber = editEvent.getTablePosition().getRow();

                    final HeroNameCustomizationVM currentlyEditedViewModel =
                            editEvent.getTableView().getItems().get(currentRowNumber);

                    currentlyEditedViewModel.setHeroName(editEvent.getNewValue());
                }
        );
    }
}
