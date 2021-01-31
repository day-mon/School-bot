package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.School;

public class RemoveSchool extends Command {

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // TODO Auto-generated method stub
        MessageChannel channel = event.getChannel();

        if (args.length != 1) {

        } else if (!SchoolGirl.schools.containsKey(args[0])) {
            
        } else {
           School school = SchoolGirl.schools.get(args[0]);
           if (school.getListOfProfessors().size() >= 1) {
               channel.sendMessage("You cannot delete this school:\n " +
                                   "\t\t Reason: ** There are professors at this school **");
           } else if (school.get)
        }

    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub
    

    }
    
    
}
