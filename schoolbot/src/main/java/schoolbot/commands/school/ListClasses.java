package schoolbot.commands.school;

import java.util.Enumeration;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;
import schoolbot.natives.util.StringOperations;

public class ListClasses extends Command {

    public ListClasses() {
        super(new String[]{"classes", "listclasses"});
    }

    public ListClasses(String [] aliases) {
        super(aliases);
    }


    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub
        System.out.println("School doesnt exit");


    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

        MessageChannel channel = event.getChannel();
        /**
         * To avoid spaming hte chat we will only ask for classes that from a specific school, subject or professor
         * args[0] = school
         * args[1] (optional) = subject / professor 
         */


         // if school exist
        if (SchoolGirl.schools.containsKey(args[0])) {
            if (args.length == 1) {
                School school = SchoolGirl.schools.get(args[0]);
                StringBuilder Classes = new StringBuilder("```");
                for (Classroom clazz : school.getListOfClasses().values()) {
                    if (clazz.getGuild() == event.getGuild()) {
                        System.out.println(clazz.getProfessor());
                        Classes.append(clazz.toString());
                    }
                    if (Classes.length() >= 1750) {
                        StringOperations.messageExtender(Classes, channel);
                    }
                }
                Classes.append("```");
                if (Classes.toString().length() < 8)  channel.sendMessage("**There are no professors for this school at the moment...**").queue(); else channel.sendMessage(Classes).queue();   

   
            }
            
        } else {
            System.out.println("School doesnt exit");
        }
    }
    
}
