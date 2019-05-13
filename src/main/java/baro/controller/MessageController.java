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
    TextField serverID, channelID, userID, authorName, authorAvatarUrl, title, titleUrl, footer, footerUrl, thumbnail,
            img, fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7,
            fieldName8, fieldName9;
    @FXML
    TextArea msg, fieldValue1, fieldValue2, fieldValue3, fieldValue4, fieldValue5, fieldValue6, fieldValue7,
            fieldValue8, fieldValue9;
    @FXML
    Label messageStatus;
    @FXML
    CheckBox embed, inline1, inline2, inline3, inline4, inline5, inline6, inline7, inline8, inline9;
    @FXML
    ColorPicker colorPicker = new ColorPicker();

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
                (float) colorPicker.getValue().getRed(),
                (float) colorPicker.getValue().getGreen(),
                (float) colorPicker.getValue().getBlue(),
                (float) colorPicker.getValue().getOpacity());

        if (embed.isSelected()) {
            eb.setColor(embedColor);
            if (msg != null && msg.getText().length() > 0)
                eb.setDescription(msg.getText());
            if (authorName != null && authorName.getText().length() > 0) {
                try {
                    eb.setAuthor(authorName.getText(), authorAvatarUrl.getText(), authorAvatarUrl.getText());
                } catch (Exception ex) {
                    eb.setAuthor(authorName.getText(), null);
                }
            }
            if (title != null && title.getText().length() > 0) {
                try {
                    eb.setTitle(title.getText(), titleUrl.getText());
                } catch (Exception ex) {
                    eb.setTitle(title.getText());
                }
            }
            if (footer != null && footer.getText().length() > 0) {
                try {
                    eb.setFooter(footer.getText(), footerUrl.getText());
                } catch (Exception ex) {
                    eb.setFooter(footer.getText(), null);
                }
            }
            if (thumbnail != null && thumbnail.getText().length() > 0) {
                try {
                    eb.setThumbnail(thumbnail.getText());
                } catch (Exception ex) {
                }
            }
            if (img != null && img.getText().length() > 0) {
                try {
                    eb.setImage(img.getText());
                } catch (Exception ex) {
                }
            }
            if (fieldName1 != null && fieldValue1 != null &&
                    fieldName1.getText().length() > 0 && fieldValue1.getText().length() > 0) {
                eb.addField(fieldName1.getText(), fieldValue1.getText(), inline1.isSelected());
            }
            if (fieldName2 != null && fieldValue2 != null &&
                    fieldName2.getText().length() > 0 && fieldValue2.getText().length() > 0) {
                eb.addField(fieldName2.getText(), fieldValue2.getText(), inline2.isSelected());
            }
            if (fieldName3 != null && fieldValue3 != null &&
                    fieldName3.getText().length() > 0 && fieldValue3.getText().length() > 0) {
                eb.addField(fieldName3.getText(), fieldValue3.getText(), inline3.isSelected());
            }
            if (fieldName4 != null && fieldValue4 != null &&
                    fieldName4.getText().length() > 0 && fieldValue4.getText().length() > 0) {
                eb.addField(fieldName4.getText(), fieldValue4.getText(), inline4.isSelected());
            }
            if (fieldName5 != null && fieldValue5 != null &&
                    fieldName5.getText().length() > 0 && fieldValue5.getText().length() > 0) {
                eb.addField(fieldName5.getText(), fieldValue5.getText(), inline5.isSelected());
            }
            if (fieldName6 != null && fieldValue6 != null &&
                    fieldName6.getText().length() > 0 && fieldValue6.getText().length() > 0) {
                eb.addField(fieldName6.getText(), fieldValue6.getText(), inline6.isSelected());
            }
            if (fieldName7 != null && fieldValue7 != null &&
                    fieldName7.getText().length() > 0 && fieldValue7.getText().length() > 0) {
                eb.addField(fieldName7.getText(), fieldValue7.getText(), inline7.isSelected());
            }
            if (fieldName8 != null && fieldValue8 != null &&
                    fieldName8.getText().length() > 0 && fieldValue8.getText().length() > 0) {
                eb.addField(fieldName8.getText(), fieldValue8.getText(), inline8.isSelected());
            }
            if (fieldName9 != null && fieldValue9 != null &&
                    fieldName9.getText().length() > 0 && fieldValue9.getText().length() > 0) {
                eb.addField(fieldName9.getText(), fieldValue9.getText(), inline9.isSelected());
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
        colorPicker.setValue(Color.CYAN);
    }
}
