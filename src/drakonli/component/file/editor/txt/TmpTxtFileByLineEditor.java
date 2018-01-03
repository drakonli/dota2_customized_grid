package drakonli.component.file.editor.txt;

import drakonli.component.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.component.file.scanner.factory.ScannerFactoryInterface;
import drakonli.component.file.writer.factory.FileWriterFactoryInterface;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class TmpTxtFileByLineEditor implements TxtFileByLineEditorInterface
{
    private static final String TMP_FILE_PREFIX = "txt_by_line_editor";
    private static final String TMP_FILE_SUFFIX = ".tmp";

    private ScannerFactoryInterface scannerFactory;
    private FileWriterFactoryInterface fileWriterFactory;

    public TmpTxtFileByLineEditor(ScannerFactoryInterface scannerFactory, FileWriterFactoryInterface fileWriterFactory)
    {
        this.scannerFactory = scannerFactory;
        this.fileWriterFactory = fileWriterFactory;
    }

    public void edit(File file, TxtLineEditorInterface editor, TxtLineForEditQualifierInterface qualifier)
            throws IOException, NoLineQualifiedForEditException
    {
        File tmpFile =
                File.createTempFile(TmpTxtFileByLineEditor.TMP_FILE_PREFIX, TmpTxtFileByLineEditor.TMP_FILE_SUFFIX);

        Scanner fileReader = this.scannerFactory.createScanner(file);
        Writer writer = this.fileWriterFactory.createWriter(tmpFile);

        Boolean fileIsQualified = false;

        while (fileReader.hasNextLine()) {
            String currentLine = fileReader.nextLine() + System.lineSeparator();

            if (!currentLine.isEmpty() && qualifier.isLineQualifiedForEdit(currentLine)) {
                fileIsQualified = true;

                currentLine = editor.editLine(currentLine);
            }

            writer.write(currentLine);
        }

        fileReader.close();
        writer.close();

        if (!fileIsQualified) {
            throw new NoLineQualifiedForEditException();
        }

        Files.move(tmpFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
