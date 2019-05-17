package baro.dialogs;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 16.05.2019 02:20
 */
public class PTextInputDialog {

    private TextInputDialog dialog;

    public TextInputDialog getInstance() {
        return dialog;
    }

    public PTextInputDialog() {
        dialog = new TextInputDialog();
        initIcon();
        initCss();
    }

    private void initIcon() {
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        String icon = "/images/DiscordLogo.png";
        stage.getIcons().add(new Image(this.getClass().getResource(icon).toString()));
    }

    private void initCss() {
        DialogPane dialogStyle = dialog.getDialogPane();
        String css = "/css/dark-theme.css";
        dialogStyle.getStylesheets().add(getClass().getResource(css).toExternalForm());
        dialogStyle.getStyleClass().add("custom-alert");
    }
}