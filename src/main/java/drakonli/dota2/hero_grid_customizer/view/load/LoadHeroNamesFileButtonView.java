package drakonli.dota2.hero_grid_customizer.view.load;

import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view.load.handler.LoadButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
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

    private final HeroGridViewModel heroGridViewModel;
    private final FileChooserFactoryInterface fileChooserFactory;
    private final NotificatorInterface notificator;
    private final List<LoadButtonHandlerInterface> loadButtonHandlers;

    public LoadHeroNamesFileButtonView(
            HeroGridViewModel heroGridViewModel,
            FileChooserFactoryInterface fileChooserFactory,
            NotificatorInterface notificator,
            List<LoadButtonHandlerInterface> loadButtonHandlers
    )
    {
        this.heroGridViewModel = heroGridViewModel;
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

        this.heroGridViewModel.setChosenHeroGridFile(file);

        try {
            for (LoadButtonHandlerInterface handler : this.loadButtonHandlers) {
                handler.handle();
            }
        } catch (HandlerException e) {
            this.notificator.error(e.getCause().getMessage());

            this.heroGridViewModel.setChosenHeroGridFile(null);
        }
    }
}
