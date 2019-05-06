package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import util.Settings;
import util.SettingsManager;

import javax.security.auth.login.LoginException;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class JavaApp extends Application {

    public static JDA api;

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
        setupBot();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/message.fxml"));
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Discord Bot GUI");
        primaryStage.getIcons().add(new Image(JavaApp.class.getResourceAsStream("/images/DiscordLogo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void setupBot() {
        try {
            Settings settings = SettingsManager.getInstance().getSettings();
            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(settings.getBotToken());
            //Login to Discord now that we are all setup.
            api = jdaBuilder.build().awaitReady();
        } catch (IllegalArgumentException e) {
            System.out.println("No login details provided! Please provide a botToken in the config.");
            System.exit(NO_USERNAME_PASS_COMBO);
        } catch (LoginException e) {
            System.out.println("The botToken provided in the Config.json was incorrect.");
            System.out.println("Did you modify the Config.json after it was created?");
            System.exit(BAD_USERNAME_PASS_COMBO);
        } catch (InterruptedException e) {
            System.out.println("Our login thread was interrupted!");
            System.exit(UNABLE_TO_CONNECT_TO_DISCORD);
        }
    }
}
