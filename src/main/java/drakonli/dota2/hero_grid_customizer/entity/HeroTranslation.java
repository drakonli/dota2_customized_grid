package drakonli.dota2.hero_grid_customizer.entity;

import java.io.Serializable;

public class HeroTranslation implements Serializable
{
    private final String heroCode;
    private final String heroName;

    public HeroTranslation(String heroCode, String heroName)
    {
        this.heroCode = heroCode;
        this.heroName = heroName;
    }

    public String getHeroCode()
    {
        return heroCode;
    }

    public String getHeroName()
    {
        return heroName;
    }
}
