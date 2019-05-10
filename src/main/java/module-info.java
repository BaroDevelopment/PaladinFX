/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 10.05.2019 13:35
 */

module PaladinFX {
    requires javafx.controls;
    requires javafx.fxml;

    opens baro.controller to javafx.fxml;
    exports baro;
}