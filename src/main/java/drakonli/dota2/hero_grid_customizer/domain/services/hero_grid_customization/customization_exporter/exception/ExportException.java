package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.exception;

public class ExportException extends Exception
{
    public ExportException(Throwable cause)
    {
        super("Export error occurred", cause);
    }
}
