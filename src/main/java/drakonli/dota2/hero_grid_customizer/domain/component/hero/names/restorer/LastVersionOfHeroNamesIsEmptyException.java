package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.restorer;

public class LastVersionOfHeroNamesIsEmptyException extends Exception
{
    public LastVersionOfHeroNamesIsEmptyException()
    {
        super("No previously stored version of hero names");
    }
}
