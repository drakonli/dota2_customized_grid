package drakonli.component.file.scanner.factory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BufferedCharsetScannerFactory implements ScannerFactoryInterface
{
    final private Charset charset;

    public BufferedCharsetScannerFactory(Charset charset)
    {
        this.charset = charset;
    }

    public Scanner createScanner(File file) throws FileNotFoundException
    {
        return new Scanner(
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file),
                                this.charset
                        )
                )
        );
    }
}
