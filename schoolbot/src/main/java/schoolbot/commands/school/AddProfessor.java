package schoolbot.commands.school;


import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.util.InvalidUsage;

public class AddProfessor extends Command {


    public AddProfessor() {
        super(new String [] {"addprofessor", "addprof", "profadd"});
    }

    public AddProfessor(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        channel.sendMessage(new InvalidUsage("https://google.com", "AddProfessor", "Correct usage: ++addprofessor **<professor name> <professor email>**", event.getMessage(), this).getInvalidUsage());

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();


        if (args.length != 3) {
            channel.sendMessage(new InvalidUsage("google.com", "AddProfessor", ":x: One or more arguments are missing", event.getMessage(), this).getInvalidUsage());
        } else {
            for (String schools : SchoolGirl.schoolCalls) {
                /**
                 * Very inefficent way to do it, will find a better way l8er.
                 */
                if (schools.equals(args[3])) {
                    SchoolGirl.professors.add(new Professor(args[0], args[1], SchoolGirl.schools.get(args[3])));
                } else {
                    channel.sendMessage(new InvalidUsage("https://google.com", "AddProfessor", "Invalid school!", event.getMessage(), this).getInvalidUsage());
                }
            }
        }

                        /* Where args[0] = name  &
                     args[1] = prefix */    
   
    }
    //TODO: do file
    

}