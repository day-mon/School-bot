package schoolbot.commands.school;


import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
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
        GuildImpl guild = (GuildImpl)event.getGuild();


        if (args.length != 4) {
            channel.sendMessage(new InvalidUsage("google.com", "AddProfessor", ":x: One or more arguments are missing", event.getMessage(), this).getInvalidUsage()).queue();;
        } else {
            for (String schools : SchoolGirl.schoolCalls) {
                /**
                 * Very inefficent way to do it, will find a better way l8er.
                 */
                if (schools.equals(args[3])) {
                    School school = SchoolGirl.schools.get(args[3]);
                    if (school.getGuild() == event.getGuild()) {
                        Professor prof = new Professor(guild, args[0], args[1], args[2], school);
                        SchoolGirl.professors.add(prof);
                        school.addProfessor(prof);
                        channel.sendMessage(":white_check_mark: Professor added succesfully :white_check_mark: ").queue();;
                        break;
                    } else {
                        // school does not belong in guild
                        break;
                    }
                }
            }
            channel.sendMessage(new InvalidUsage("https://google.com", "AddProfessor", "Invalid school!", event.getMessage(), this).getInvalidUsage());
        }

                        /* Where args[0] = name  &
                     args[1] = prefix */    
   
    }
    //TODO: do file
    

}