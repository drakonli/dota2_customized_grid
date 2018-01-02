package drakonli.dota2.hero_grid_customizer.view_model.hero.translation;

import javafx.beans.property.SimpleStringProperty;

public class HeroTranslationViewModel
{
    private final SimpleStringProperty heroName = new SimpleStringProperty("");
    private final SimpleStringProperty heroCode = new SimpleStringProperty("");

    public HeroTranslationViewModel(String name, String code)
    {
        this.setHeroName(name);
        this.setHeroCode(code);
    }

    public String getHeroName() {
        return this.heroName.get();
    }

    public void setHeroName(String name) {
        this.heroName.set(name);
    }

    public String getHeroCode() {
        return this.heroCode.get();
    }

    public void setHeroCode(String code) {
        this.heroCode.set(code);
    }
}
