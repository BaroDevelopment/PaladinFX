package baro.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import baro.core.JavaApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class MessageController implements Initializable {

    @FXML
    JFXTextArea messageTextArea;
    @FXML
    JFXButton sendMessageButton;
    @FXML
    JFXTextField channelID;
    @FXML
    JFXTextField guildID;
    @FXML
    JFXTextField userID;
    @FXML
    JFXListView<String> dms;

    public static ObservableList<String> directMessage = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dms.setItems(directMessage);
    }

    @FXML
    private void sendDMMessage(ActionEvent e) {
        try {
            String message = messageTextArea.getText();
            User user = JavaApp.api.getUserById(userID.getText());
            if (user != null && message.length() > 0) {
                user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    private void sendServerMessage() {
        try {
            Guild guild = JavaApp.api.getGuildById(guildID.getText());
            TextChannel channel = guild.getTextChannelById(channelID.getText());
            String message = messageTextArea.getText();

            if (channel != null && message.length() > 0) {
                channel.sendMessage(message).queue();
            }
        } catch (Exception ignored) {
        }
    }
}
