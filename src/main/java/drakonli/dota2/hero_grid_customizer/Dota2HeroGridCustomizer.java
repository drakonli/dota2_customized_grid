package drakonli.dota2.hero_grid_customizer;

import drakonli.dota2.hero_grid_customizer.view.main.MainView;
import drakonli.jcomponents.notificator.AlertNotificator;
import drakonli.jcomponents.notificator.NotificatorInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dota2HeroGridCustomizer extends Application {
    private final NotificatorInterface notificator = new AlertNotificator();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try {
            Parent graph = FXMLLoader.load(MainView.class.getResource("MainView.fxml"));

            primaryStage.setTitle("Dota 2 Grid Customization");
            primaryStage.setScene(new Scene(graph));
            primaryStage.show();

        } catch (IOException exception) {
            notificator.error(exception.getMessage());
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
