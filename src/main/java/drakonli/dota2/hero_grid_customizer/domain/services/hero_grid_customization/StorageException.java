package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization;

public class StorageException extends Exception
{
    public StorageException(Throwable cause)
    {
        super("Storage error", cause);
    }
}
