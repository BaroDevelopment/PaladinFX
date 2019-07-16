package baro;

import baro.util.Settings;
import baro.util.SettingsManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import org.apache.commons.io.FilenameUtils;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;


/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class JavaApp extends Application {

    public static JDA api;

    public static final int NO_USERNAME_PASS_COMBO = 32;

    public static void main(String[] args) throws IOException {
        setupBot();
        launch(args);

//        Guild guild = api.getGuildById("485901002931699726");
//        for (TextChannel c : guild.getTextChannels()) {
//            BufferedWriter output;
//            File file = new File("messages/" + c.getName() + ".txt");
//            output = new BufferedWriter(new FileWriter(file));
//            c.getIterableHistory().takeAsync(1000).join().forEach(message -> {
//                String time = "[" + message.getCreationTime().getHour() + ":" + message.getCreationTime().getMinute() + ":" + message.getCreationTime().getSecond() + "]";
//                try {
//                    if (message.getEmbeds().isEmpty()) {
//                        output.write(time + " " + message.getAuthor().getName() + ":\t\t\t" + message.getContentDisplay());
//                    } else {
//                        output.write(time + " " + message.getAuthor().getName() + ":\t\t\t Embed Message - ChannelID: " + c.getId() + "  - Msg ID: " + message.getId());
//                    }
//                    if (!message.getAttachments().isEmpty() && message.getAttachments().get(0).isImage()) {
//                        output.write(time + " " + message.getAuthor().getName() + ":\t\t\t[Attachmant] " + message.getAttachments().get(0).getUrl());
//                    }
//                    output.write("\n\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            output.close();
//        }

//        TextChannel ektos = api.getTextChannelById("588883689253896192");
//        try (Stream<String> stream = Files.lines(Paths.get("ektos.txt"))) {
//            stream.forEach(s -> {
//                ektos.sendMessage(s).queue();
//            });
//        }
    }


    private static void setupBot() {

        try {
            Settings settings = SettingsManager.getInstance().getSettings();
            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(settings.getBotToken());
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

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
