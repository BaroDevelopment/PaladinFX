package baro.controller;

import baro.JavaApp;
import baro.model.GuildInfoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.dv8tion.jda.core.entities.Guild;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 14.05.2019 10:20
 */
public class GuildInfoController implements Initializable {

    @FXML
    TableView<GuildInfoModel> guildInfoView;

    @FXML
    TableColumn<GuildInfoModel, String> guildName;

    @FXML
    TableColumn<GuildInfoModel, String> guildID;

    @FXML
    TableColumn<GuildInfoModel, String> guildOwner;

    @FXML
    TableColumn<GuildInfoModel, Integer> guildBotsCount;

    @FXML
    TableColumn<GuildInfoModel, Integer> guildTotalMembers;

    private ObservableList<GuildInfoModel> guildInfoModels = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //make sure the property value factory is exactly same as the e.g getGuildName from your model class
        guildName.setCellValueFactory(new PropertyValueFactory<>("GuildName"));
        guildID.setCellValueFactory(new PropertyValueFactory<>("GuildID"));
        guildOwner.setCellValueFactory(new PropertyValueFactory<>("GuildOwner"));
        guildBotsCount.setCellValueFactory(new PropertyValueFactory<>("GuildBotsCount"));
        guildTotalMembers.setCellValueFactory(new PropertyValueFactory<>("GuildTotalMembers"));

        // init observable list
        for (Guild g : JavaApp.api.getGuilds()) {
            int bots = (int) g.getMembers().stream().filter(m -> m.getUser().isBot()).count();
            GuildInfoModel m = new GuildInfoModel(g.getName(), g.getId(), g.getOwner().getUser().getName(), bots, g.getMembers().size());
            guildInfoModels.add(m);
            guildInfoView.getItems().add(m);
        }
        guildInfoView.setItems(guildInfoModels);
    }
}

//
