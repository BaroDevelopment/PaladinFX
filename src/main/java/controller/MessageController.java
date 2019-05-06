package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import core.JavaApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class MessageController {

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
    private void sendMessage(ActionEvent e){
        Guild guild = JavaApp.api.getGuildById(guildID.getText());
        TextChannel channel = guild.getTextChannelById(channelID.getText());
        String message = messageTextArea.getText();

        if (channel != null && message.length() > 0){
            channel.sendMessage(message).queue();
        }
    }
}
