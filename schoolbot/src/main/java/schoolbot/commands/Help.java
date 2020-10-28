package schoolbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.natives.util.StringOperations;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import schoolbot.natives.util.StringOperations;

public class Help extends Command {

    public Help(){
        super(new String[]{"help","h"});
    }

    public Help(String[] aliases){
        super(aliases);
    }
    
    @Override
    public void run(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        // Send generic help, we'll deal with later.
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        if(args.length >= 1){
            String commandID = StringOperations.removeBounds(args[0]);
            for (Command com : SchoolGirl.getCommands().values()) {
                if(com.isInCalls(commandID)){
                    channel.sendMessage(com.getDocumentation()).queue();
                }
            }    
        }
    }


}
