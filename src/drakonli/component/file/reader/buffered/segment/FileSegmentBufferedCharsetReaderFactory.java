package drakonli.component.file.reader.buffered.segment;

import drakonli.component.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.component.filter.FilterInterface;

import java.io.*;
import java.nio.charset.Charset;

public class FileSegmentBufferedCharsetReaderFactory implements BufferedFileReaderFactoryInterface
{
    final private FilterInterface<String> skipFromLineMatcher;
    final private FilterInterface<String> skipToLineMatcher;
    final private Charset charset;

    public FileSegmentBufferedCharsetReaderFactory(
            FilterInterface<String> skipFromLineMatcher,
            FilterInterface<String> skipToLineMatcher,
            Charset charset
    )
    {
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;
        this.charset = charset;
    }

    @Override
    public BufferedReader createFileReader(File file) throws IOException, InvalidSkipToMatcherException
    {
        return new FileSegmentBufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        this.charset
                ),
                this.skipFromLineMatcher,
                this.skipToLineMatcher
        );
    }
}
