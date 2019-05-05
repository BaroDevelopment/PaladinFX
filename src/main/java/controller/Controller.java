package controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 05.05.2019 19:30
 */
public class Controller implements Initializable {

    @FXML
    JFXListView<String> listView;
    ObservableList<String> obsListView = FXCollections.observableArrayList("Message", "Guild");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(obsListView);

    }
}
