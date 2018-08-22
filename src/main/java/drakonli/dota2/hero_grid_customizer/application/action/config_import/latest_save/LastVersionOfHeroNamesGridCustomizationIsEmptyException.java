package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

public class LastVersionOfHeroNamesGridCustomizationIsEmptyException extends Exception
{
    public LastVersionOfHeroNamesGridCustomizationIsEmptyException()
    {
        super("No previously stored version of hero names grid");
    }
}
