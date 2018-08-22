package drakonli.dota2.hero_grid_customizer.domain.model;

import java.io.Serializable;

public class HeroNameCustomization implements Serializable
{
    private final String heroCode;
    private final String heroName;

    public HeroNameCustomization(String heroCode, String heroName)
    {
        this.heroCode = heroCode;
        this.heroName = heroName;
    }

    public String getHeroCode()
    {
        return this.heroCode;
    }

    public String getHeroName()
    {
        return this.heroName;
    }
}
