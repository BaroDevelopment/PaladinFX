import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class JavaApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Paladin GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
