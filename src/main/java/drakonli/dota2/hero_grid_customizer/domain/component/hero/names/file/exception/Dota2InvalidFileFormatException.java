package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception;

import drakonli.jcomponents.file.exception.InvalidFileFormatException;

public class Dota2InvalidFileFormatException extends InvalidFileFormatException
{
    public Dota2InvalidFileFormatException()
    {
        super("File is not a dota2 translations file");
    }
}
