package drakonli.dota2.hero_grid_customizer.view.hero_translations_table;

import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HeroTranslationsTableView implements Initializable
{
    public TableView<HeroTranslationViewModel> heroTranslationsTableView;
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
        this.heroGridViewModel.setHeroTranslations(this.heroTranslationsTableView.getItems());
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
