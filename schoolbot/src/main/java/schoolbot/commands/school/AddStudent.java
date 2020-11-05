package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Student;
import schoolbot.natives.util.InvalidUsage;

public class AddStudent extends Command {

    public AddStudent() {
        super(new String[] {"addstudent"});
    }

    public AddStudent(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        GuildImpl guild = (GuildImpl)event.getGuild();
        /**
         * TODO: fix usage "look below for correct usage"
         * TODO: fix command (half finished)
         */
        if (args.length != 4) {
            event.getChannel().sendMessage(new InvalidUsage("https://google.com", "AddStudent", "Incorrect usage ** Look below for correct usage**", event.getMessage(), this).getInvalidUsage()).queue();
        } else if (!SchoolGirl.schoolCalls.contains(args[2])) {
            event.getChannel().sendMessage(new InvalidUsage("https://google.com", "AddStudent", "School does not exist", event.getMessage(), this).getInvalidUsage()).queue();
        } 
        else {
            SchoolGirl.students.add(new Student(guild, SchoolGirl.schools.get(args[1]), Double.parseDouble(args[2]), new String[] {args[3]}, args[0]));        
        }

    }
    
}
