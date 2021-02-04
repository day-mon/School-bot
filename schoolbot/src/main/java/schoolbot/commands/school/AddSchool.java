package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class AddSchool extends Command {

    public AddSchool() {
        super(new String[] { "addschool", "as" });
    }

    public AddSchool(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        GuildImpl guild = (GuildImpl) event.getGuild();

        boolean valid = true;

        if (args[0].length() < 10 || SchoolGirl.schools.containsKey(args[2])) {
            valid = false;
        }

        // args[0] is full schoolname
        // args[1] is school email suffix
        // args[2] is name that used to refrence the school
        if (args.length != 3) {
            MessageOperations.invalidUsageShortner(
                    "https://github.com/tykoooo/School-bot/blob/master/schoolbot/src/main/java/schoolbot/commands/school/AddSchool.java",
                    "Incorrect usage: **Look below for correct usage", event.getMessage(), this);
        } else if (valid) {
            SchoolGirl.schoolCalls.add(args[2]);
            SchoolGirl.schools.putIfAbsent(args[2], new School(guild, args[0], args[1]));

            File schools = new File("schoolbot\\src\\main\\files\\schools.ser");
            File schoolsCalls = new File("schoolbot\\src\\main\\files\\schoolCalls.ser");

            FileOperations.writeToFile(schools, (Object) SchoolGirl.schools);
            FileOperations.writeToFile(schoolsCalls, (Object) SchoolGirl.schoolCalls);

            channel.sendMessage(":white_check_mark: School added succesfully :white_check_mark: ").queue();
        } else {
            MessageOperations.invalidUsageShortner(
                    "https://github.com/tykoooo/School-bot/blob/master/schoolbot/src/main/java/schoolbot/commands/school/AddSchool.java",
                    "This is not a valid school or school already exist", event.getMessage(), this);
        }
    }

}