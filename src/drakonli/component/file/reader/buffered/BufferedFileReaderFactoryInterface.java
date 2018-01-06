package drakonli.component.file.reader.buffered;

import drakonli.component.file.reader.buffered.segment.InvalidSkipToMatcherException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public interface BufferedFileReaderFactoryInterface
{
    BufferedReader createFileReader(File file) throws IOException, InvalidSkipToMatcherException;
}
