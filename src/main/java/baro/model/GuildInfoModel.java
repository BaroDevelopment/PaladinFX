package baro.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 14.05.2019 23:33
 */
public class GuildInfoModel {

    private SimpleStringProperty guildName;
    private SimpleStringProperty guildID;
    private SimpleStringProperty guildOwner;
    private SimpleIntegerProperty guildBotsCount;
    private SimpleIntegerProperty guildTotalMembers;

    public GuildInfoModel(String guildName, String guildID, String guildOwner, int guildBotsCount, int guildTotalMembers) {
        this.guildName = new SimpleStringProperty(guildName);
        this.guildID = new SimpleStringProperty(guildID);
        this.guildOwner = new SimpleStringProperty(guildOwner);
        this.guildBotsCount = new SimpleIntegerProperty(guildBotsCount);
        this.guildTotalMembers = new SimpleIntegerProperty(guildTotalMembers);
    }

    public String getGuildName() {
        return guildName.get();
    }

    public SimpleStringProperty guildNameProperty() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName.set(guildName);
    }

    public String getGuildID() {
        return guildID.get();
    }

    public SimpleStringProperty guildIDProperty() {
        return guildID;
    }

    public void setGuildID(String guildID) {
        this.guildID.set(guildID);
    }

    public String getGuildOwner() {
        return guildOwner.get();
    }

    public SimpleStringProperty guildOwnerProperty() {
        return guildOwner;
    }

    public void setGuildOwner(String guildOwner) {
        this.guildOwner.set(guildOwner);
    }

    public int getGuildBotsCount() {
        return guildBotsCount.get();
    }

    public SimpleIntegerProperty guildBotsCountProperty() {
        return guildBotsCount;
    }

    public void setGuildBotsCount(int guildBotsCount) {
        this.guildBotsCount.set(guildBotsCount);
    }

    public int getGuildTotalMembers() {
        return guildTotalMembers.get();
    }

    public SimpleIntegerProperty guildTotalMembersProperty() {
        return guildTotalMembers;
    }

    public void setGuildTotalMembers(int guildTotalMembers) {
        this.guildTotalMembers.set(guildTotalMembers);
    }
}
