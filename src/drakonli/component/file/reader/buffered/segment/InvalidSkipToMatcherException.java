package drakonli.component.file.reader.buffered.segment;

public class InvalidSkipToMatcherException extends Exception
{
    public InvalidSkipToMatcherException()
    {
        super("Line to skip to was not found in file");
    }
}
