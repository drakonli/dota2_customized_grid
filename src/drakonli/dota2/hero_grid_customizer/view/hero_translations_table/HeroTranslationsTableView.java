package drakonli.dota2.hero_grid_customizer.view.hero_translations_table;

import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HeroTranslationsTableView
{
    public TableView<HeroTranslationViewModel> heroTranslationsTableView;
    public TableColumn<HeroTranslationViewModel, String> heroNameColumn;

    public void init(HeroGridViewModel heroGridViewModel)
    {
        heroGridViewModel.setHeroTranslations(heroTranslationsTableView.getItems());

        this.handleHeroNameColumnEdit();
    }

    private void handleHeroNameColumnEdit()
    {
        heroNameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<HeroTranslationViewModel, String> editEvent) -> {
                    final int currentRowNumber = editEvent.getTablePosition().getRow();

                    final HeroTranslationViewModel currentlyEditedViewModel =
                            editEvent.getTableView().getItems().get(currentRowNumber);

                    currentlyEditedViewModel.setHeroName(editEvent.getNewValue());
                }
        );
    }
}
