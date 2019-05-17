package baro.controller;

import baro.JavaApp;
import baro.dialogs.PAlert;
import baro.dialogs.PTextInputDialog;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 14.05.2019 10:20
 * <p>
 * Examples for Dialogs/Alerts: https://code.makery.ch/blog/javafx-dialogs-official/
 */
public class ManagerController implements Initializable {

    @FXML
    ProgressBar emoteDownloadProgressBar;

    @FXML
    Button emoteDownloadButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void leaveServer(ActionEvent e) {
        PTextInputDialog pTextInputDialog = new PTextInputDialog();
        TextInputDialog dialog = pTextInputDialog.getInstance();

        dialog.setTitle("Leave Server");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the server id:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            PAlert paladinAlert = new PAlert(Alert.AlertType.INFORMATION);
            Alert alert = paladinAlert.getInstance();
            alert.setTitle("Result");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            try {
                JavaApp.api.getGuildById(name).leave().queue();
                alert.setContentText("You have successfully left the server!");
            } catch (Exception ex) {
                alert.setContentText("Failed to leave the server.");
            } finally {
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void downloadEmotes(ActionEvent event) {
        emoteDownloadButton.setDisable(true);
        emoteDownloadProgressBar.setVisible(true);
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                String emoteDirectory = (new File("").getAbsolutePath() + "/emotes/");
                createFolderIfNotPresent(emoteDirectory);
                for (int i = 0; i < JavaApp.api.getGuilds().size(); i++) {
                    emoteDownloadProgressBar.setProgress((double) i / JavaApp.api.getGuilds().size());
                    Guild g = JavaApp.api.getGuilds().get(i);
                    if (g.getEmotes().size() > 0) {
                        String directory = g.getId();
                        createFolderIfNotPresent(emoteDirectory + directory);
                        for (Emote e : g.getEmotes()) {
                            try {
                                downloadImage(e.getImageUrl(), emoteDirectory + directory);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
                return null;
            }
        };

//        test.progressProperty().bind(task.progressProperty());

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void createFolderIfNotPresent(String folderName) {
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void downloadImage(String sourceUrl, String targetDirectory)
            throws IOException {
        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(new FileOutputStream(targetDirectory + File.separator + FilenameUtils.getName(sourceUrl)))) {
            int readByte;
            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        }
    }
}
