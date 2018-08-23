package drakonli.dota2.hero_grid_customizer.application.view_model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HeroGridCustomizationVM
{
    private ObservableList<HeroNameCustomizationVM> heroNameCustomizationVMList = FXCollections.observableArrayList();

    public ObservableList<HeroNameCustomizationVM> getHeroNameCustomizationVMList()
    {
        return this.heroNameCustomizationVMList;
    }
}
