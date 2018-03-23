package drakonli.dota2.hero_grid_customizer.application.view_model.grid;

import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HeroGridViewModel
{
    private ObservableList<HeroTranslationViewModel> heroTranslations = FXCollections.observableArrayList();

    public ObservableList<HeroTranslationViewModel> getHeroTranslations()
    {
        return this.heroTranslations;
    }

    public void setHeroTranslations(ObservableList<HeroTranslationViewModel> heroTranslations)
    {
        this.heroTranslations = heroTranslations;
    }
}
