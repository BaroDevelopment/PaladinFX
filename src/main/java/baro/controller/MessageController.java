package baro.controller;

import baro.JavaApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import net.dv8tion.jda.core.EmbedBuilder;
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
    TextField serverID, channelID, userID, authorName, authorAvatarUrl, title, titleUrl, footer, footerUrl, thumbnail, img;
    @FXML
    TextArea msg;
    @FXML
    Label messageStatus;
    @FXML
    CheckBox embed;
    @FXML
    ColorPicker color;

    @FXML
    ListView<String> dmHistory;
    public static ObservableList<String> dmHistoryList;

    @FXML
    void sendMessage(ActionEvent e) {

        boolean validServer = isValidServer();
        boolean validUser = isValidUser();

        if (isValidServer() && isValidUser())
            return;

        EmbedBuilder eb = new EmbedBuilder();
        java.awt.Color embedColor = new java.awt.Color(
                (int) color.getValue().getRed() * 255,
                (int) color.getValue().getGreen() * 255,
                (int) color.getValue().getBlue() * 255);

        if (embed.isSelected()) {
            eb.setColor(embedColor);
            if (msg != null && msg.getText().length() > 0)
                eb.setDescription(msg.getText());
            if (authorName != null && authorName.getText().length() > 0) {
                try {
                    eb.setAuthor(authorName.getText(), authorAvatarUrl.getText(), authorAvatarUrl.getText());
                }catch (Exception ex){
                    eb.setAuthor(authorName.getText(), null);
                }
            } if (title != null && title.getText().length() > 0){
                try {
                    eb.setTitle(title.getText(), titleUrl.getText());
                }catch (Exception ex){
                    eb.setTitle(title.getText());
                }
            }
            if (footer != null && footer.getText().length() > 0){
                try {
                    eb.setFooter(footer.getText(), footerUrl.getText());
                }catch (Exception ex){
                    eb.setFooter(footer.getText(), null);
                }
            }
            if (thumbnail != null && thumbnail.getText().length() > 0){
                try {
                    eb.setThumbnail(thumbnail.getText());
                }catch (Exception ex){
                }
            }
            if (img != null && img.getText().length() > 0){
                try {
                    eb.setImage(img.getText());
                }catch (Exception ex){
                }
            }
        }
        if (msg.getText().isEmpty()) {
            messageStatus.setStyle("-fx-text-fill: #ff5353");
            messageStatus.setText("message is empty");
        } else if (validServer) {
            Guild guild = JavaApp.api.getGuildById(serverID.getText());
            TextChannel textChannel = guild.getTextChannelById(channelID.getText());
            String message = msg.getText();
            if (embed.isSelected())
                textChannel.sendMessage(eb.build()).queue();
            else
                textChannel.sendMessage(message).queue();
        } else if (validUser) {
            User user = JavaApp.api.getUserById(userID.getText());
            user.openPrivateChannel().queue(privateChannel -> {
                if (embed.isSelected())
                    privateChannel.sendMessage(eb.build()).queue();
                else
                    privateChannel.sendMessage(msg.getText()).queue();
            });
        }
    }

    boolean isValidUser() {
        User user;
        try {
            user = JavaApp.api.getUserById(userID.getText());
            if (user == null) {
                messageStatus.setStyle("-fx-text-fill: #ff5353");
                messageStatus.setText("Invalid User ID");
                return false;
            } else {
                messageStatus.setStyle("-fx-text-fill: #38ff87");
                messageStatus.setText("message sent");
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    boolean isValidServer() {
        Guild guild;
        TextChannel textChannel;
        messageStatus.setStyle("-fx-text-fill: #ff5353");
        try {
            guild = JavaApp.api.getGuildById(serverID.getText());
            if (guild == null)
                messageStatus.setText("Invalid Server ID");
        } catch (Exception ex) {
            messageStatus.setText("Invalid Server ID");
            return false;
        }
        try {
            textChannel = guild.getTextChannelById(channelID.getText());
            if (textChannel == null)
                messageStatus.setText("Invalid Channel ID");
        } catch (Exception ex) {
            messageStatus.setText("Invalid Channel ID");
            return false;
        }
        messageStatus.setStyle("-fx-text-fill: #38ff87");
        messageStatus.setText("message sent");
        return textChannel != null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dmHistoryList = FXCollections.observableArrayList();
        dmHistory.setItems(dmHistoryList);
        color = new ColorPicker(Color.MAGENTA);
    }
}
