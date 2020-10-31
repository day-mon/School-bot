package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.util.InvalidUsage;
import schoolbot.natives.util.StringOperations;

public class AddSchool extends Command {

    public AddSchool() {
        super(new String[] {"addschool", "as"});
        this.documentation = StringOperations.parseDoc("schoolbot\\docs\\AddSchool.txt");

    }

    public AddSchool(String[] aliases) {
        super(aliases);
        this.documentation = StringOperations.parseDoc("schoolbot\\docs\\AddSchool.txt");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();

        //args[0] is Full 
        //args[1] is 
        //args[1] is
            if (args.length != 3) {
                InvalidUsage usage = new InvalidUsage("https://google.com", "AddSchool", "Incorrect usage: 'Correct usage: ++addschool <schoolname> <school email> <school refrence>" , event.getMessage());
                event.getChannel().sendMessage(usage.getInvalidUsage()).queue();
            } else {
                if (args[0].length() < 10) 
                    channel.sendMessage("That is not a valid school name.");
                SchoolGirl.schools.putIfAbsent(args[2], new School(args[0], args[1]));
            }
    }

}