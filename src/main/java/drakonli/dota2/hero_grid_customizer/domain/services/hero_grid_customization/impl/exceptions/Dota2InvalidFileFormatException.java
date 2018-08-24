package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.exceptions;

import drakonli.jcomponents.file.exception.InvalidFileFormatException;

public class Dota2InvalidFileFormatException extends InvalidFileFormatException
{
    public Dota2InvalidFileFormatException()
    {
        super("File is not a dota2 translations file");
    }
}
