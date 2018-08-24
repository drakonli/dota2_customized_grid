package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.import_customization.latest_save;

public class LastVersionOfHeroGridCustomizationIsEmptyException extends Exception
{
    public LastVersionOfHeroGridCustomizationIsEmptyException()
    {
        super("No previously stored version of hero grid");
    }
}
