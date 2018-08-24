package drakonli.dota2.hero_grid_customizer.application.models;

import javafx.beans.property.SimpleStringProperty;

public class HeroNameCustomizationVM
{
    private final SimpleStringProperty heroName = new SimpleStringProperty("");
    private final SimpleStringProperty heroCode = new SimpleStringProperty("");

    public HeroNameCustomizationVM(String name, String code)
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
