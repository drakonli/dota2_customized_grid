package drakonli.dota2.hero_grid_customizer.domain.services.export;

public class ExportException extends Exception
{
    public ExportException(Throwable cause)
    {
        super("Export error occurred", cause);
    }
}
