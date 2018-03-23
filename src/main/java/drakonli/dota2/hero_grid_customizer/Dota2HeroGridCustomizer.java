package drakonli.dota2.hero_grid_customizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Dota2HeroGridCustomizer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("di/services.xml");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/main/MainView.fxml"));
            loader.setControllerFactory(context::getBean);

            primaryStage.setTitle("Dota 2 Grid Customization");
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();

        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
