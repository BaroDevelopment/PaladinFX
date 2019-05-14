package baro.controller;

import baro.JavaApp;
import baro.util.Regional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.webhook.WebhookClient;
import net.dv8tion.jda.webhook.WebhookClientBuilder;
import net.dv8tion.jda.webhook.WebhookMessage;
import net.dv8tion.jda.webhook.WebhookMessageBuilder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class MessageController implements Initializable {

    @FXML
    TextField serverID, channelID, userID, webhookName, webhookAvatar;
    @FXML
    TextArea msg;
    @FXML
    Label messageStatus;
    @FXML
    CheckBox embedEnabled, webhook, regional;
    @FXML
    ColorPicker colorPicker = new ColorPicker();

    @FXML
    private EmbedContentController embedController;

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

        if (embedEnabled.isSelected()) {
            eb.setColor(embedColor);
            if (msg != null && msg.getText().length() > 0)
                eb.setDescription(msg.getText());
            if (embedController.getAuthorName() != null && embedController.getAuthorName().getText().length() > 0) {
                try {
                    eb.setAuthor(embedController.getAuthorName().getText(), embedController.getAuthorAvatarUrl().getText(), embedController.getAuthorAvatarUrl().getText());
                } catch (Exception ex) {
                    eb.setAuthor(embedController.getAuthorName().getText(), null);
                }
            }
            if (embedController.getTitle() != null && embedController.getTitle().getText().length() > 0) {
                try {
                    eb.setTitle(embedController.getTitle().getText(), embedController.getTitleUrl().getText());
                } catch (Exception ex) {
                    eb.setTitle(embedController.getTitle().getText());
                }
            }
            if (embedController.getFooter() != null && embedController.getFooter().getText().length() > 0) {
                try {
                    eb.setFooter(embedController.getFooter().getText(), embedController.getFooterUrl().getText());
                } catch (Exception ex) {
                    eb.setFooter(embedController.getFooter().getText(), null);
                }
            }
            if (embedController.getThumbnail() != null && embedController.getThumbnail().getText().length() > 0) {
                try {
                    eb.setThumbnail(embedController.getThumbnail().getText());
                } catch (Exception ex) {
                }
            }
            if (embedController.getImg() != null && embedController.getImg().getText().length() > 0) {
                try {
                    eb.setImage(embedController.getImg().getText());
                } catch (Exception ex) {
                }
            }
            if (embedController.getFieldName1() != null && embedController.getFieldValue1() != null &&
                    embedController.getFieldName1().getText().length() > 0 && embedController.getFieldValue1().getText().length() > 0) {
                eb.addField(embedController.getFieldName1().getText(), embedController.getFieldValue1().getText(), embedController.getInline1().isSelected());
            }
            if (embedController.getFieldName2() != null && embedController.getFieldValue2() != null &&
                    embedController.getFieldName2().getText().length() > 0 && embedController.getFieldValue2().getText().length() > 0) {
                eb.addField(embedController.getFieldName2().getText(), embedController.getFieldValue2().getText(), embedController.getInline2().isSelected());
            }
            if (embedController.getFieldName3() != null && embedController.getFieldValue3() != null &&
                    embedController.getFieldName3().getText().length() > 0 && embedController.getFieldValue3().getText().length() > 0) {
                eb.addField(embedController.getFieldName3().getText(), embedController.getFieldValue3().getText(), embedController.getInline3().isSelected());
            }
            if (embedController.getFieldName4() != null && embedController.getFieldValue4() != null &&
                    embedController.getFieldName4().getText().length() > 0 && embedController.getFieldValue4().getText().length() > 0) {
                eb.addField(embedController.getFieldName4().getText(), embedController.getFieldValue4().getText(), embedController.getInline4().isSelected());
            }
            if (embedController.getFieldName5() != null && embedController.getFieldValue5() != null &&
                    embedController.getFieldName5().getText().length() > 0 && embedController.getFieldValue5().getText().length() > 0) {
                eb.addField(embedController.getFieldName5().getText(), embedController.getFieldValue5().getText(), embedController.getInline5().isSelected());
            }
            if (embedController.getFieldName6() != null && embedController.getFieldValue6() != null &&
                    embedController.getFieldName6().getText().length() > 0 && embedController.getFieldValue6().getText().length() > 0) {
                eb.addField(embedController.getFieldName6().getText(), embedController.getFieldValue6().getText(), embedController.getInline6().isSelected());
            }
            if (embedController.getFieldName7() != null && embedController.getFieldValue7() != null &&
                    embedController.getFieldName7().getText().length() > 0 && embedController.getFieldValue7().getText().length() > 0) {
                eb.addField(embedController.getFieldName7().getText(), embedController.getFieldValue7().getText(), embedController.getInline7().isSelected());
            }
            if (embedController.getFieldName8() != null && embedController.getFieldValue8() != null &&
                    embedController.getFieldName8().getText().length() > 0 && embedController.getFieldValue8().getText().length() > 0) {
                eb.addField(embedController.getFieldName8().getText(), embedController.getFieldValue8().getText(), embedController.getInline8().isSelected());
            }
            if (embedController.getFieldName9() != null && embedController.getFieldValue9() != null &&
                    embedController.getFieldName9().getText().length() > 0 && embedController.getFieldValue9().getText().length() > 0) {
                eb.addField(embedController.getFieldName9().getText(), embedController.getFieldValue9().getText(), embedController.getInline9().isSelected());
            }
        }
        if (msg.getText().isEmpty() && !embedEnabled.isSelected() && !webhook.isSelected()) {
            messageStatus.setStyle("-fx-text-fill: #ff5353");
            messageStatus.setText("message is empty");
        } else if (webhook.isSelected() && validServer) {
            try {
                Guild guild = JavaApp.api.getGuildById(serverID.getText());
                TextChannel textChannel = guild.getTextChannelById(channelID.getText());
                Webhook webhook = textChannel.getWebhooks().complete().size() == 0 ?
                        textChannel.createWebhook("PaladinFX").complete() :
                        textChannel.getWebhooks().complete().get(0);
                WebhookClientBuilder webhookClientBuilder = webhook.newClient();
                WebhookClient client = webhookClientBuilder.build(); //remember to close this client when you are done

                WebhookMessageBuilder builder = new WebhookMessageBuilder();
                if (embedEnabled.isSelected())
                    builder.addEmbeds(eb.build());
                else
                    builder.setContent(msg.getText());
                builder.setUsername(webhookName.getText());
                builder.setAvatarUrl(webhookAvatar.getText());
                WebhookMessage message = builder.build();
                client.send(message);
                client.close();
            } catch (Exception ex) {
                messageStatus.setStyle("-fx-text-fill: #ff5353");
                messageStatus.setText("No Webhook found");
            }
        } else if (validServer) {
            Guild guild = JavaApp.api.getGuildById(serverID.getText());
            TextChannel textChannel = guild.getTextChannelById(channelID.getText());
            if (embedEnabled.isSelected())
                textChannel.sendMessage(eb.build()).queue();
            else if (regional.isSelected()) {
                Regional regional = new Regional();
                textChannel.sendMessage(regional.toRegional(msg.getText())).queue();
            } else
                textChannel.sendMessage(msg.getText()).queue();
        } else if (validUser) {
            User user = JavaApp.api.getUserById(userID.getText());
            user.openPrivateChannel().queue(privateChannel -> {
                if (embedEnabled.isSelected())
                    privateChannel.sendMessage(eb.build()).queue();
                else if (regional.isSelected()) {
                    Regional regional = new Regional();
                    privateChannel.sendMessage(regional.toRegional(msg.getText())).queue();
                } else
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

    @FXML
    private void disableEmbed(ActionEvent e) {
        if (regional.isSelected()) {
            embedEnabled.setDisable(true);
            embedEnabled.setSelected(false);
            webhook.setDisable(true);
            webhook.setSelected(false);
        } else {
            embedEnabled.setDisable(false);
            webhook.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorPicker.setValue(Color.CYAN);
    }
}
