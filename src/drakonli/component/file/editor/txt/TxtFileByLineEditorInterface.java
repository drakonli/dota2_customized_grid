package drakonli.component.file.editor.txt;

import drakonli.component.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.matcher.LineToEditMatcherInterface;

import java.io.File;
import java.io.IOException;

public interface TxtFileByLineEditorInterface
{
    void edit(File file, TxtLineEditorInterface lineEditor, LineToEditMatcherInterface lineToEditMatcher)
            throws IOException, NoLineQualifiedForEditException;
}
