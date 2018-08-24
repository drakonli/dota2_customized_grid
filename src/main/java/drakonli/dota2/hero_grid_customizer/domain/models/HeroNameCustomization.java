package drakonli.dota2.hero_grid_customizer.domain.models;

import java.io.Serializable;

public class HeroNameCustomization implements Serializable
{
    private final String heroNameUID;
    private final String heroName;

    public HeroNameCustomization(String heroNameUID, String heroName)
    {
        this.heroNameUID = heroNameUID;
        this.heroName = heroName;
    }

    public String getHeroNameUID()
    {
        return this.heroNameUID;
    }

    public String getHeroName()
    {
        return this.heroName;
    }
}
