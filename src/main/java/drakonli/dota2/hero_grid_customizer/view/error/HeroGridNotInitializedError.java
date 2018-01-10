package drakonli.dota2.hero_grid_customizer.view.error;

public class HeroGridNotInitializedError extends Error
{
    public HeroGridNotInitializedError()
    {
        super("Hero grid ViewModel was not initialized");
    }
}
