package drakonli.dota2.hero_grid_customizer.view.load;

import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view.load.handler.LoadButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.jcomponents.file.chooser.FileChooserFactoryInterface;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.File;
import java.util.List;

public class LoadHeroNamesFileButtonView
{
    public Button loadButton;

    private HeroGridViewModel heroGridViewModel;
    private FileChooserFactoryInterface fileChooserFactory;
    private NotificatorInterface notificator;
    private List<LoadButtonHandlerInterface> loadButtonHandlers;

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
            for (LoadButtonHandlerInterface handler : loadButtonHandlers) {
                handler.handle();
            }
        } catch (HandlerException e) {
            notificator.error(e.getCause().getMessage());

            this.heroGridViewModel.setChosenHeroGridFile(null);
        }
    }
}
