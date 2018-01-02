package drakonli.dota2.hero_grid_customizer.component.hero.names.restorer;

public class LastVersionOfHeroNamesIsEmpty extends Exception
{
    public LastVersionOfHeroNamesIsEmpty()
    {
        super("No previously stored version of hero names");
    }
}
