package drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception;

import drakonli.jcomponents.file.exception.InvalidFileFormatException;

public class Dota2InvalidFileFormatException extends InvalidFileFormatException
{
    public Dota2InvalidFileFormatException()
    {
        super("Chosen file has wrong format. Please, choose dota2 translation file");
    }
}
