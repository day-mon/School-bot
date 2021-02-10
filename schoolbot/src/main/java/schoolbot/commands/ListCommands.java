package schoolbot.commands;

import java.util.Date;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.MessageOperations;

public class ListCommands extends Command {
    
    public ListCommands() {
        super(new String[] {"listcommands", "commands", "coms", "listcoms"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        StringBuilder s = new StringBuilder("");


        for (Command coms : Ryan.getCommands().values()) {
            s.append("`" + coms.getName() + "`, ");
        }
        s.deleteCharAt(s.length()-2);


        MessageOperations.embedAsMessage("Commands", new Field("List of commands in [" + msg.getGuild().getName() + "] ", s.toString(), true), "Generated on: " + new Date(), msg);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // TODO Auto-generated method stub

    }


    
    
}
