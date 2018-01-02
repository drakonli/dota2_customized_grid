package drakonli.dota2.hero_grid_customizer.view.restore;

import drakonli.component.notificator.NotificatorInterface;
import drakonli.dota2.hero_grid_customizer.view.error.HeroGridNotInitializedError;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view.restore.handler.RestoreButtonHandlerInterface;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;

public class RestoreHeroNamesButtonView
{
    public Button restoreButton;

    private HeroGridViewModel heroGridViewModel;
    private NotificatorInterface notificator;
    private List<RestoreButtonHandlerInterface> restoreButtonHandlers;

    public void init(
            HeroGridViewModel heroGridViewModel,
            NotificatorInterface notificator,
            List<RestoreButtonHandlerInterface> restoreButtonHandlers
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.restoreButtonHandlers = restoreButtonHandlers;
        this.notificator = notificator;

        this.changeRestoreButtonManagementOnHeroCollectionChange();
    }

    private void changeRestoreButtonManagementOnHeroCollectionChange()
    {
        restoreButton.setManaged(false);

        heroGridViewModel.getHeroTranslations().addListener(new ListChangeListener<HeroTranslationViewModel>() {
            public void onChanged(Change<? extends HeroTranslationViewModel > change) {
                restoreButton.setManaged(!change.getList().isEmpty());
            }
        });
    }

    public void onRestoreClick(ActionEvent actionEvent)
    {
        if (null == this.heroGridViewModel) {
            throw new HeroGridNotInitializedError();
        }

        try {
            for (RestoreButtonHandlerInterface handler : restoreButtonHandlers) {
                handler.handle(this.heroGridViewModel);
            }

            notificator.success("Restore success!");
        } catch (HandlerException e) {
            notificator.error(e.getCause().getMessage());
        }
    }
}
