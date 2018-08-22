package drakonli.dota2.hero_grid_customizer.ui.customization_menu_bar.import_customization;

import drakonli.dota2.hero_grid_customizer.ui.handler.HideMenuOnHiddenChildrenHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportCustomizationMenuView implements Initializable
{
    @FXML
    public Menu importMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        (new HideMenuOnHiddenChildrenHandler()).handleMenu(this.importMenu);
    }
}
