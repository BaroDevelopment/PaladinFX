package baro;

import baro.util.Settings;
import baro.util.SettingsManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;


/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class JavaApp extends Application {

    public static JDA api;

    public static final int NO_USERNAME_PASS_COMBO = 32;

    public static void main(String[] args) {
        setupBot();
        launch(args);
    }

    private static void setupBot() {

        try {
            Settings settings = SettingsManager.getInstance().getSettings();
            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(settings.getBotToken());
            jdaBuilder.addEventListener(new EventListener());
            api = jdaBuilder.build().awaitReady();
        } catch (IllegalArgumentException e) {
            System.out.println("No login details provided! Please provide a botToken in the config.");
            System.exit(NO_USERNAME_PASS_COMBO);
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n" +
                "---------------------------------\n" +
                "Logged in as: " + api.getSelfUser().getName() + "#" + api.getSelfUser().getDiscriminator() + "\n" +
                "UserID: " + api.getSelfUser().getId() + "\n" +
                "---------------------------------\n\n"
        );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/message.fxml"));
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("PaladinFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DiscordLogo.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
