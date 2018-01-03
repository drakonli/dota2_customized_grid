package drakonli.component.file.scanner.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface ScannerFactoryInterface
{
    Scanner createScanner(File file) throws FileNotFoundException;
}
