package drakonli.component.file.editor.txt;

import java.io.File;
import java.io.IOException;

public interface TxtFileByLineEditorInterface
{
    void edit(File file, TxtLineEditorInterface editor, TxtLineForEditQualifierInterface qualifier) throws IOException;
}
