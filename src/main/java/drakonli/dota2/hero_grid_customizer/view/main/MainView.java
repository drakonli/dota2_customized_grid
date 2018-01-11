package drakonli.dota2.hero_grid_customizer.view.main;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exporter.HeroNamesIntoFileExporter;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.view.save.SaveHeroNamesIntoFileButtonView;
import drakonli.dota2.hero_grid_customizer.view.save.handler.BackupHeroTranslationFileHandler;
import drakonli.dota2.hero_grid_customizer.view.save.handler.ReplaceHeroNamesInTranslationsFileHandler;
import drakonli.dota2.hero_grid_customizer.view.save.handler.SaveButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view.save.handler.StoreHeroNamesHandler;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;
import drakonli.jcomponents.file.backuper.FileBackuper;
import drakonli.jcomponents.file.editor.txt.tmp.TmpTxtFileByLineEditor;
import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.jcomponents.file.reader.buffered.charset.BufferedCharsetFileReaderFactory;
import drakonli.jcomponents.file.writer.factory.BufferedCharsetFileWriterFactory;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainView implements Initializable
{
    private static final Charset DOTA2_TRANSLATION_FILE_CHARSET = StandardCharsets.UTF_16LE;
    private static final String TMP_DOTA2_FILE_PREFIX = "txt_by_line_editor";
    private static final String TMP_DOTA2_FILE_SUFFIX = ".tmp";

    @FXML
    private SaveHeroNamesIntoFileButtonView saveHeroNamesButtonController;

    // multiple-times injections
    // note: one-time injections are injected directly into a class with "new"
    private HeroGridViewModel heroGridViewModel;
    private HeroNamesByFileStorage heroNamesByFileStorage;
    private NotificatorInterface notificator;
    private BufferedFileReaderFactoryInterface dota2TranslationsFileReaderFactory;
    private HeroTranslationByFileLineExtractor heroTranslationViewModelByFileLineExtractor;
    private HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;

    public MainView(NotificatorInterface notificator, HeroGridViewModel heroGridViewModel)
    {
        this.notificator = notificator;
        this.heroGridViewModel = heroGridViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.heroNamesByFileStorage = new HeroNamesByFileStorage();
        this.dota2TranslationsFileReaderFactory = new BufferedCharsetFileReaderFactory(DOTA2_TRANSLATION_FILE_CHARSET);
        this.heroTranslationViewModelByFileLineExtractor = new HeroTranslationByFileLineExtractor();
        this.heroTranslationViewModelsToEntityMapper = new HeroTranslationViewModelsToEntityMapper();

        this.initSaveHeroNamesButtonController();
    }

    private void initSaveHeroNamesButtonController()
    {
        List<SaveButtonHandlerInterface> saveHeroNamesButtonHandlers = new ArrayList<>();
        saveHeroNamesButtonHandlers.add(
                new BackupHeroTranslationFileHandler(new FileBackuper())
        );
        saveHeroNamesButtonHandlers.add(
                new StoreHeroNamesHandler(
                        this.heroNamesByFileStorage,
                        this.heroTranslationViewModelsToEntityMapper
                )
        );
        saveHeroNamesButtonHandlers.add(
                new ReplaceHeroNamesInTranslationsFileHandler(
                        new HeroNamesIntoFileExporter(
                                new TmpTxtFileByLineEditor(
                                        TMP_DOTA2_FILE_PREFIX,
                                        TMP_DOTA2_FILE_SUFFIX,
                                        this.dota2TranslationsFileReaderFactory,
                                        new BufferedCharsetFileWriterFactory(DOTA2_TRANSLATION_FILE_CHARSET)
                                ),
                            this.heroTranslationViewModelByFileLineExtractor
                        ),
                        this.heroTranslationViewModelsToEntityMapper
                )
        );

        this.saveHeroNamesButtonController.init(this.heroGridViewModel, this.notificator, saveHeroNamesButtonHandlers);
    }
}
