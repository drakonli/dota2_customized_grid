package drakonli.dota2.hero_grid_customizer.application.actions;

/**
 * Exception for general application actions
 */
public class ApplicationActionException extends Exception
{
    public ApplicationActionException(Throwable cause)
    {
        super(cause.getMessage(), cause);
    }
}
