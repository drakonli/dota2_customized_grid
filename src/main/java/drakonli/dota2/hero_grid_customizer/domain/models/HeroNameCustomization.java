package drakonli.dota2.hero_grid_customizer.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class HeroNameCustomization implements Serializable
{
    @Id
    @GeneratedValue
    private int id;

    private String heroNameUID;
    private String heroName;

    public HeroNameCustomization()
    {
    }

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
