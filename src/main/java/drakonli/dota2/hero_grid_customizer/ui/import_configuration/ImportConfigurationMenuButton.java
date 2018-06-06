package drakonli.dota2.hero_grid_customizer.ui.import_configuration;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportConfigurationMenuButton implements Initializable
{
    @FXML
    public Menu import_button_menu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.import_button_menu.setVisible(this.isMenuButtonShouldBeVisible());

        for (MenuItem menuItem : this.import_button_menu.getItems()) {
            menuItem.visibleProperty().addListener(
                    (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
                        this.import_button_menu.setVisible(this.isMenuButtonShouldBeVisible())
            );
        }
    }

    private Boolean isMenuButtonShouldBeVisible()
    {
        for (MenuItem menuItem : this.import_button_menu.getItems()) {
            if (menuItem.isVisible()) {
                return true;
            }
        }

        return false;
    }
}
