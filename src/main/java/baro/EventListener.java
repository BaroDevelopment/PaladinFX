package baro;

import baro.controller.MessageController;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 12.05.2019 00:54
 */
public class EventListener extends ListenerAdapter {

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        try {
            MessageController.dmHistoryList.add(
                    event.getMessage().getCreationTime().getHour() +
                            ":" + event.getMessage().getCreationTime().getMinute() +
                            ":" + event.getMessage().getCreationTime().getSecond() + "  [" +
                            event.getAuthor().getName() + " ]   " +
                            event.getMessage().getContentDisplay()
            );
        }catch (Exception ex){
        }
    }
}
