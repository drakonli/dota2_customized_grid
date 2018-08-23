package drakonli.dota2.hero_grid_customizer.ui.view.menu_bar.import_customization;

import drakonli.dota2.hero_grid_customizer.ui.utilities.handler.HideMenuOnHiddenChildrenHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportGridCustomizationMenuView implements Initializable
{
    @FXML
    public Menu importMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        (new HideMenuOnHiddenChildrenHandler()).handleMenu(this.importMenu);
    }
}
