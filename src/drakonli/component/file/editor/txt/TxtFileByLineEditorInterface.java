package drakonli.component.file.editor.txt;

import drakonli.component.file.editor.txt.exception.NoLineQualifiedForEditException;

import java.io.File;
import java.io.IOException;

public interface TxtFileByLineEditorInterface
{
    void edit(File file, TxtLineEditorInterface lineEditor, TxtLineForEditQualifierInterface qualifier)
            throws IOException, NoLineQualifiedForEditException;
}
