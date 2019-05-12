package baro.controller;

import baro.JavaApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class MessageController implements Initializable {

    @FXML
    TextField serverID, channelID, userID;
    @FXML
    TextArea serverMessage, userMessage;

    @FXML
    ListView<String> dmHistory;
    public static ObservableList<String> dmHistoryList;

    @FXML
    void sendServerMessage(ActionEvent e){
        try {
            JavaApp.api.getGuildById(serverID.getText()).getTextChannelById(channelID.getText()).sendMessage(serverMessage.getText()).queue();
        }catch (Exception ex){
            System.out.println("Failed to send message");
        }
    }

    @FXML
    void sendUserMessage(ActionEvent e){
        try {
            JavaApp.api.getUserById(userID.getText()).openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(userMessage.getText()).queue();
            });
        }catch (Exception ex){
            System.out.println("Failed to send message");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dmHistoryList = FXCollections.observableArrayList();
        dmHistory.setItems(dmHistoryList);
    }
}
