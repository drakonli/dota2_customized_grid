package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

public class LastVersionOfHeroGridCustomizationIsEmptyException extends Exception
{
    public LastVersionOfHeroGridCustomizationIsEmptyException()
    {
        super("No previously stored version of hero grid");
    }
}
