package baro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;


/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class JavaApp extends Application {

//    public static JDA api;

    public static final int NORMAL_SHUTDOWN = 10;
    public static final int RESTART_EXITCODE = 11;
    public static final int NEWLY_CREATED_CONFIG = 12;

    //Non error, action required exit codes.
    public static final int UPDATE_LATEST_EXITCODE = 20;
    public static final int UPDATE_RECOMMENDED_EXITCODE = 21;

    //error exit codes.
    public static final int UNABLE_TO_CONNECT_TO_DISCORD = 30;
    public static final int BAD_USERNAME_PASS_COMBO = 31;
    public static final int NO_USERNAME_PASS_COMBO = 32;

    public static void main(String[] args) {
//        setupBot();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getResource("/fxml/message.fxml"));
        System.out.println(getClass().getClassLoader().getResource("/fxml/message.fxml"));
//        System.out.println(getClass().getResourceAsStream("/images/DiscordLogo.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/message.fxml"));
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("PaladinFX");
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DiscordLogo.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
