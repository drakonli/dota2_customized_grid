package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.export_customization;

import drakonli.dota2.hero_grid_customizer.ui.utilities.handler.HideMenuOnHiddenChildrenHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class ExportGridCustomizationMenuButtonView implements Initializable
{
    @FXML
    public Menu export_button_menu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        (new HideMenuOnHiddenChildrenHandler()).handleMenu(this.export_button_menu);
    }
}
