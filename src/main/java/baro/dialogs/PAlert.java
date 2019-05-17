package baro.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 16.05.2019 02:07
 */
public class PAlert {

    private Alert alert;

    public Alert getInstance() {
        return alert;
    }

    public PAlert(Alert.AlertType type) {
        alert = new Alert(type);
        initIcon();
        initCss();
    }

    private void initIcon() {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        String icon = "/images/DiscordLogo.png";
        stage.getIcons().add(new Image(this.getClass().getResource(icon).toString()));
    }

    private void initCss() {
        DialogPane dialogPane = alert.getDialogPane();
        String css = "/css/dark-theme.css";
        dialogPane.getStylesheets().add(getClass().getResource(css).toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
    }
}
