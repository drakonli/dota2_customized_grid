package drakonli.dota2.hero_grid_customizer.ui.hero_translations_table;

import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HeroTranslationsTableView implements Initializable
{
    @FXML
    public TableView<HeroTranslationViewModel> tableView;
    @FXML
    public TableColumn<HeroTranslationViewModel, String> heroNameColumn;

    private final HeroGridViewModel heroGridViewModel;

    public HeroTranslationsTableView(
            HeroGridViewModel heroGridViewModel
    )
    {
        this.heroGridViewModel = heroGridViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.tableView.itemsProperty().bind(new SimpleListProperty<>(this.heroGridViewModel.getHeroTranslationsViewModels()));
        this.handleHeroNameColumnEdit();
    }

    private void handleHeroNameColumnEdit()
    {
        this.heroNameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<HeroTranslationViewModel, String> editEvent) -> {
                    final int currentRowNumber = editEvent.getTablePosition().getRow();

                    final HeroTranslationViewModel currentlyEditedViewModel =
                            editEvent.getTableView().getItems().get(currentRowNumber);

                    currentlyEditedViewModel.setHeroName(editEvent.getNewValue());
                }
        );
    }
}
