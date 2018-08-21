package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage;

public class StorageException extends Exception
{
    public StorageException(Throwable cause)
    {
        super("Storage error", cause);
    }
}
