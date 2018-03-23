package drakonli.dota2.hero_grid_customizer.view_model.hero.grid;

import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

public class HeroGridViewModel
{
    private File chosenHeroGridFile;

    private ObservableList<HeroTranslationViewModel> heroTranslations = FXCollections.observableArrayList();

    public ObservableList<HeroTranslationViewModel> getHeroTranslations()
    {
        return this.heroTranslations;
    }

    public void setHeroTranslations(ObservableList<HeroTranslationViewModel> heroTranslations)
    {
        this.heroTranslations = heroTranslations;
    }

    public File getChosenHeroGridFile()
    {
        return this.chosenHeroGridFile;
    }

    public void setChosenHeroGridFile(File chosenHeroGridFile)
    {
        this.chosenHeroGridFile = chosenHeroGridFile;
    }
}
