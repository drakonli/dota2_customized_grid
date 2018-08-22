package drakonli.dota2.hero_grid_customizer.ui.handler;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HideMenuOnHiddenChildrenHandler
{
    public void handleMenu(Menu menu)
    {
        menu.setVisible(this.isMenuButtonShouldBeVisible(menu));

        for (MenuItem menuItem : menu.getItems()) {
            menuItem.visibleProperty().addListener(
                    (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
                            menu.setVisible(this.isMenuButtonShouldBeVisible(menu))
            );
        }
    }

    private Boolean isMenuButtonShouldBeVisible(Menu menu)
    {
        for (MenuItem menuItem : menu.getItems()) {
            if (menuItem.isVisible()) {
                return true;
            }
        }

        return false;
    }
}
