package drakonli.dota2.hero_grid_customizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Dota2HeroGridCustomizer extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("di/*");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/view/MainView.fxml"));
        loader.setControllerFactory(context::getBean);

        primaryStage.setTitle("Dota 2 Hero Grid Customization");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
