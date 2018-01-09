package drakonli.dota2.hero_grid_customizer.view.main;

import drakonli.component.file.backuper.FileBackuper;
import drakonli.component.file.chooser.GuessedDirectoryTxtFileChooserFactory;
import drakonli.component.file.editor.txt.tmp.TmpTxtFileByLineEditor;
import drakonli.component.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.component.file.reader.buffered.charset.BufferedCharsetFileReaderFactory;
import drakonli.component.file.scanner.factory.BufferedCharsetScannerFactory;
import drakonli.component.file.writer.factory.BufferedCharsetFileWriterFactory;
import drakonli.component.notificator.AlertNotificator;
import drakonli.component.notificator.NotificatorInterface;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationViewModelByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer.HeroNamesFileImporter;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.replacer.HeroNamesInFileReplacer;
import drakonli.dota2.hero_grid_customizer.component.hero.names.restorer.HeroNamesByFileStorageRestorer;
import drakonli.dota2.hero_grid_customizer.component.hero.names.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.view.hero_translations_table.HeroTranslationsTableView;
import drakonli.dota2.hero_grid_customizer.view.load.LoadHeroNamesFileButtonView;
import drakonli.dota2.hero_grid_customizer.view.load.handler.AddHeroTranslationsByFileHandler;
import drakonli.dota2.hero_grid_customizer.view.load.handler.LoadButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view.restore.RestoreHeroNamesButtonView;
import drakonli.dota2.hero_grid_customizer.view.restore.handler.RestoreButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view.restore.handler.RestoreHeroNamesHandler;
import drakonli.dota2.hero_grid_customizer.view.save.SaveHeroNamesIntoFileButtonView;
import drakonli.dota2.hero_grid_customizer.view.save.handler.BackupHeroTranslationFileHandler;
import drakonli.dota2.hero_grid_customizer.view.save.handler.ReplaceHeroNamesInTranslationsFileHandler;
import drakonli.dota2.hero_grid_customizer.view.save.handler.SaveButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view.save.handler.StoreHeroNamesHandler;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainView implements Initializable
{
    private static final String[] INITIAL_DIRECTORY_GUESSES = {
            "C:\\Program Files (x86)\\Steam\\steamapps\\common\\dota 2 beta\\game\\dota\\resource",
            "C:\\Program Files\\Steam\\steamapps\\common\\dota 2 beta\\game\\dota\\resource"
    };

    private static final String HERO_TRANSLATION_LINE_PATTERN = "\"(npc_dota_hero_[^_]*)\".*\"(.*)\"";

    @FXML
    private LoadHeroNamesFileButtonView loadHeroNamesButtonController;

    @FXML
    private SaveHeroNamesIntoFileButtonView saveHeroNamesButtonController;

    @FXML
    private RestoreHeroNamesButtonView restoreHeroNamesButtonController;

    @FXML
    private HeroTranslationsTableView heroTranslationsTableController;

    // multiple-times injections
    // note: one-time injections are injected directly into a class with "new"
    private HeroGridViewModel heroGridViewModel;
    private HeroNamesByFileStorage heroNamesByFileStorage;
    private NotificatorInterface notificator;
    private BufferedCharsetScannerFactory dota2FilesScannerFactory;
    private BufferedFileReaderFactoryInterface dota2TranslationsFileReaderFactory;
    private HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelByFileLineExtractor;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.heroNamesByFileStorage = new HeroNamesByFileStorage();
        this.heroGridViewModel = new HeroGridViewModel();
        this.notificator = new AlertNotificator();
        this.dota2FilesScannerFactory = new BufferedCharsetScannerFactory(StandardCharsets.UTF_16LE);
        this.dota2TranslationsFileReaderFactory = new BufferedCharsetFileReaderFactory(StandardCharsets.UTF_16LE);
        this.heroTranslationViewModelByFileLineExtractor = new HeroTranslationViewModelByFileLineExtractor();

        this.initHeroTranslationsTableController();
        this.initLoadHeroNamesButtonController();
        this.initSaveHeroNamesButtonController();
        this.initRestoreHeroNamesButtonController();
    }

    private void initHeroTranslationsTableController()
    {
        this.heroTranslationsTableController.init(this.heroGridViewModel);
    }

    private void initLoadHeroNamesButtonController()
    {
        List<LoadButtonHandlerInterface> loadButtonHandlers = new ArrayList<>();
        loadButtonHandlers.add(
                new AddHeroTranslationsByFileHandler(
                        new HeroNamesFileImporter(
                                this.dota2TranslationsFileReaderFactory,
                                this.heroTranslationViewModelByFileLineExtractor
                        )
                )
        );

        this.loadHeroNamesButtonController.init(
                this.heroGridViewModel,
                new GuessedDirectoryTxtFileChooserFactory(MainView.INITIAL_DIRECTORY_GUESSES),
                this.notificator,
                loadButtonHandlers
        );
    }

    private void initSaveHeroNamesButtonController()
    {
        HeroTranslationViewModelsToEntityConverter converter = new HeroTranslationViewModelsToEntityConverter();

        List<SaveButtonHandlerInterface> saveHeroNamesButtonHandlers = new ArrayList<>();
        saveHeroNamesButtonHandlers.add(
                new BackupHeroTranslationFileHandler(new FileBackuper())
        );
        saveHeroNamesButtonHandlers.add(
                new StoreHeroNamesHandler(
                        new HeroNamesByFileStorage(),
                        converter
                )
        );
        saveHeroNamesButtonHandlers.add(
                new ReplaceHeroNamesInTranslationsFileHandler(
                        new HeroNamesInFileReplacer(
                                new TmpTxtFileByLineEditor(
                                        this.dota2FilesScannerFactory,
                                        new BufferedCharsetFileWriterFactory(StandardCharsets.UTF_16LE)
                                ),
                            this.heroTranslationViewModelByFileLineExtractor
                        ),
                        converter
                )
        );

        this.saveHeroNamesButtonController.init(this.heroGridViewModel, this.notificator, saveHeroNamesButtonHandlers);
    }

    private void initRestoreHeroNamesButtonController()
    {
        List<RestoreButtonHandlerInterface> restoreButtonHandlers = new ArrayList<>();
        restoreButtonHandlers.add(
                new RestoreHeroNamesHandler(new HeroNamesByFileStorageRestorer(this.heroNamesByFileStorage))
        );

        this.restoreHeroNamesButtonController.init(this.heroGridViewModel, this.notificator, restoreButtonHandlers);
    }
}
