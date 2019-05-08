package baro.listener;

import baro.controller.MessageController;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 07.05.2019 01:26
 */
public class EventListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event){
        if (!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())){
            String hour = String.valueOf(event.getMessage().getCreationTime().getHour() + 2);
            String minute = String.valueOf(event.getMessage().getCreationTime().getMinute());
            String time = "[" + hour + ":" + minute + "] ";
            String content = time + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay();
            try{
                MessageController.directMessage.add(content);
            }catch (Exception ex){
            }
        }
    }
}
