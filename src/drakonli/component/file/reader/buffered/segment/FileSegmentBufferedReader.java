package drakonli.component.file.reader.buffered.segment;

import drakonli.component.filter.FilterInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * BufferedReader that skips reading lines to a certain matched line (fromLine) and reads until
 * another matched line (toLine). This is to optimize reading the file - when you don't really need to work with all
 * contents of the file, but only with a segment
 */
public class FileSegmentBufferedReader extends BufferedReader
{
    private FilterInterface<String> skipToLineMatcher;
    private FilterInterface<String> skipFromLineMatcher;

    public FileSegmentBufferedReader(
            Reader in, int sz,
            FilterInterface<String> skipFromLineMatcher,
            FilterInterface<String> skipToLineMatcher
    ) throws IOException, InvalidSkipToMatcherException
    {
        super(in, sz);
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;

        this.skipStart();
    }

    public FileSegmentBufferedReader(
            Reader in,
            FilterInterface<String> skipFromLineMatcher,
            FilterInterface<String> skipToLineMatcher
    ) throws IOException, InvalidSkipToMatcherException
    {
        super(in);
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;

        this.skipStart();
    }

    private void skipStart() throws IOException, InvalidSkipToMatcherException
    {
        String currentLine;
        while (null != (currentLine = this.readLine())) {
            if (this.skipFromLineMatcher.match(currentLine)) {
                return;
            }
        }

        throw new InvalidSkipToMatcherException();
    }

    @Override
    public String readLine() throws IOException
    {
        String currentLine = super.readLine();

        if (null != currentLine && this.skipToLineMatcher.match(currentLine)) {
            return null;
        }

        return currentLine;
    }
}
