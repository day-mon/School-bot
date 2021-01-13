package schoolbot.commands.school;

import java.util.Enumeration;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;
import schoolbot.natives.util.MessageOperations;
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
         * To avoid spaming the chat we will only ask for classes that are from a specific school, subject or professor
         * args[0] = school
         * args[1] (optional) = subject / professor 
         */


        if (args.length == 1) { // check lengths first
            if (SchoolGirl.schools.containsKey(args[0])) { // if school exist
                School school = SchoolGirl.schools.get(args[0]);
                StringBuilder Classes = new StringBuilder("```");
                for (Classroom clazz : school.getListOfClasses().values()) { // we need a better way to catch a class by attribute
                    System.out.println(clazz.getProfessor() + " <- profcheck");
                    
                    try {
                        Classes.append(clazz.toString());
                        if (Classes.length() >= 1750) {
                            MessageOperations.messageExtender(Classes, channel);
                        }  
                    } catch (Exception e) {
                        System.out.println("[DEBUG] A field of the given class was null for some reason.");
                        channel.sendMessage("*Yikes!* Something went wrong, I'll get back to you!").queue();
                    }
                    
                }
                Classes.append("```");
                if (Classes.toString().length() < 8)  channel.sendMessage("**There are no professors for this school at the moment...**").queue(); else channel.sendMessage(Classes).queue();   

   
            }
            
        } else {
            System.out.println("School doesn't exit");
        }
    }
    
}
