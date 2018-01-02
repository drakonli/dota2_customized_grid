package drakonli.dota2.hero_grid_customizer.view.save;

import drakonli.component.notificator.NotificatorInterface;
import drakonli.dota2.hero_grid_customizer.view.error.HeroGridNotInitializedError;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view.save.handler.SaveButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;

public class SaveHeroNamesIntoFileButtonView
{
    public Button saveButton;

    private HeroGridViewModel heroGridViewModel;
    private NotificatorInterface notificator;
    private List<SaveButtonHandlerInterface> saveButtonHandlers;

    public void init(
            HeroGridViewModel heroGridViewModel,
            NotificatorInterface notificator,
            List<SaveButtonHandlerInterface> saveButtonHandlers
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.saveButtonHandlers = saveButtonHandlers;
        this.notificator = notificator;

        this.changeSaveButtonManagementOnHeroCollectionChange();
    }

    private void changeSaveButtonManagementOnHeroCollectionChange()
    {
        saveButton.setManaged(false);

        heroGridViewModel.getHeroTranslations().addListener(new ListChangeListener<HeroTranslationViewModel>() {
            public void onChanged(Change<? extends HeroTranslationViewModel > change) {
                saveButton.setManaged(!change.getList().isEmpty());
            }
        });
    }

    public void onSaveClick(ActionEvent actionEvent) throws HeroGridNotInitializedError
    {
        if (null == this.heroGridViewModel) {
            throw new HeroGridNotInitializedError();
        }

        try {
            for (SaveButtonHandlerInterface handler : this.saveButtonHandlers) {
                handler.handle(this.heroGridViewModel);
            }

            notificator.success("Save success!");
        } catch (HandlerException e) {
            notificator.error(e.getCause().getMessage());
        }
    }
}
