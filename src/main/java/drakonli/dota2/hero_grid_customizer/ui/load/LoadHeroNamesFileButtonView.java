package drakonli.dota2.hero_grid_customizer.ui.load;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_handler.load.LoadButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.application.view_model.export_import.file.ExportImportHeroGridByFileViewModel;
import drakonli.jcomponents.file.chooser.FileChooserFactoryInterface;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.util.List;

public class LoadHeroNamesFileButtonView
{
    @FXML
    public Button loadButton;

    private final ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel;
    private final FileChooserFactoryInterface fileChooserFactory;
    private final NotificatorInterface notificator;
    private final List<LoadButtonHandlerInterface> loadButtonHandlers;

    public LoadHeroNamesFileButtonView(
            ExportImportHeroGridByFileViewModel exportImportHeroGridByFileViewModel,
            FileChooserFactoryInterface fileChooserFactory,
            NotificatorInterface notificator,
            List<LoadButtonHandlerInterface> loadButtonHandlers
    )
    {
        this.exportImportHeroGridByFileViewModel = exportImportHeroGridByFileViewModel;
        this.fileChooserFactory = fileChooserFactory;
        this.notificator = notificator;
        this.loadButtonHandlers = loadButtonHandlers;
    }

    public void onLoadClick(ActionEvent actionEvent)
    {
        File file = this.fileChooserFactory.createFileChooser().showOpenDialog(this.loadButton.getScene().getWindow());

        if (null == file) {
            return;
        }

        this.exportImportHeroGridByFileViewModel.setChosenHeroGridFile(file);

        try {
            for (LoadButtonHandlerInterface handler : this.loadButtonHandlers) {
                handler.handle();
            }
        } catch (HandlerException e) {
            this.notificator.error(e.getCause().getMessage());

            this.exportImportHeroGridByFileViewModel.setChosenHeroGridFile(null);
        }
    }
}