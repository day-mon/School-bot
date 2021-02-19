package schoolbot.commands;

import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;

public class Annoy extends Command {


    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        
        /**
         * Args[0] = user 
         */
        User user = event.getAuthor();

         if (args.length >= 1) {
             List<User> memifyList = event.getMessage().getMentionedUsers();
             if (memifyList.size() > 0) {
                 for (User users : memifyList) {
                    if (Ryan.memify.containsKey(user)) {
                        boolean check = Ryan.memify.get(user);
                        check ? Ryan.memify.replace(user, true, false) : Ryan.memify.replace(user, false, true); 
                    } else {
                        Ryan.memify.put(user, true);

                    }
                }

             }

         }

    }
    
}
