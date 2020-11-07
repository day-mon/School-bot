package schoolbot.commands;

import java.util.Arrays;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.natives.util.StringOperations;

public class Help extends Command {

    public Help(){
        super(new String[]{"help","h"});
        this.documentation = StringOperations.parseDoc("schoolbot\\docs\\Help.txt");
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
                    try {
                    channel.sendMessage(com.getDocumentation()).queue();
                    } catch (IllegalArgumentException e) {
                        if (e.toString().startsWith("java.lang.IllegalArgumentException: Provided emb"))  {
                            channel.sendMessage("There is no documentation for this command..\n\t\t\t\t **Contact  damon#9999 or Elsklivet#886**").queue();
                        }
                    }
                }
            }    
        }
    }


}
