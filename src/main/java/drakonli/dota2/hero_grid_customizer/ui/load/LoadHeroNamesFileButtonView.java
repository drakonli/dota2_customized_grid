package drakonli.dota2.hero_grid_customizer.ui.load;

import drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file.IImportConfigFromFileAction;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroGridViewModel;
import drakonli.jcomponents.file.chooser.FileChooserFactoryInterface;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;

public class LoadHeroNamesFileButtonView
{
    @FXML
    public Button loadButton;

    private final FileChooserFactoryInterface fileChooserFactory;
    private final NotificatorInterface notificator;
    private final IImportConfigFromFileAction importConfigFromFileAction;
    private final HeroGridViewModel heroGridViewModel;

    public LoadHeroNamesFileButtonView(
            FileChooserFactoryInterface fileChooserFactory,
            NotificatorInterface notificator,
            IImportConfigFromFileAction importConfigFromFileAction,
            HeroGridViewModel heroGridViewModel
    )
    {
        this.fileChooserFactory = fileChooserFactory;
        this.notificator = notificator;
        this.importConfigFromFileAction = importConfigFromFileAction;
        this.heroGridViewModel = heroGridViewModel;
    }

    public void onLoadClick(ActionEvent actionEvent)
    {
        File file = this.fileChooserFactory.createFileChooser().showOpenDialog(this.loadButton.getScene().getWindow());

        if (null == file) {
            return;
        }

        try {
            this.importConfigFromFileAction.importConfig(file, this.heroGridViewModel.getHeroTranslationsViewModels());
        } catch (Exception e) {
            this.notificator.error(e.getCause().getMessage());
        }
    }
}
