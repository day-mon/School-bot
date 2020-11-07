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

        boolean validName = true;

         
        if (args[0].length() < 10) {
            validName = false;
        }

        //args[0] is full schoolname
        //args[1] is school email suffix
        //args[2] is name that used to refrence the school
        //TODO: fix usage "look below for correct usage"
            if (args.length != 3) {
                InvalidUsage usage = new InvalidUsage("https://github.com/tykoooo/School-bot/blob/master/schoolbot/src/main/java/schoolbot/commands/school/AddSchool.java", "AddSchool", "Incorrect usage: **Look below for correct usage", event.getMessage(), this);
                event.getChannel().sendMessage(usage.getInvalidUsage()).queue();
            } else if (validName) { 
                SchoolGirl.schoolCalls.add(args[2]);
                SchoolGirl.schools.putIfAbsent(args[2], new School(args[0], args[1]));
                channel.sendMessage(":white_check_mark: School added succesfully :white_check_mark: ").queue();;
            } else {
                event.getChannel().sendMessage(new InvalidUsage("https://github.com/tykoooo/School-bot/blob/master/schoolbot/src/main/java/schoolbot/commands/school/AddSchool.java", "AddSchool", "That is not a valid school", event.getMessage(), this).getInvalidUsage()).queue();;
            }
    }

}