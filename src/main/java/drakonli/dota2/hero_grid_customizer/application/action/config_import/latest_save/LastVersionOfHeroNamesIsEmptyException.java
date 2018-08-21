package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

public class LastVersionOfHeroNamesIsEmptyException extends Exception
{
    public LastVersionOfHeroNamesIsEmptyException()
    {
        super("No previously stored version of hero names");
    }
}
