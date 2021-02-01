package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class RemoveSchool extends Command {

    public RemoveSchool() {
        super(new String[] { "removeschool", "schoolremove", "rschool" }, "RemoveSchool");
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // TODO Auto-generated method stub
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();
        File schools = new File("schoolbot\\src\\main\\files\\schools.ser");
        File schoolCalls = new File("schoolbot\\src\\main\\files\\schoolCalls.ser");

        if (args.length != 1) {

        } else if (!SchoolGirl.schools.containsKey(args[0])) {
            MessageOperations.invalidUsageShortner("https://google.com", "That school doesnt exist!", msg, this);
            return;
        } else {
            School school = SchoolGirl.schools.get(args[0]);
            if (school.getListOfProfessors().size() <= 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **professors** at this school", msg, this);
                return;
            } else if (school.getListOfClasses().size() <= 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **classes** at this school", msg, this);
                return;
            } else if (school.getListOfStudents().size() <= 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **professors** at this school", msg, this);
                return;
            }

            SchoolGirl.schools.remove(args[0]);
            SchoolGirl.schoolCalls.remove(args[0]);
            FileOperations.writeToFile(schoolCalls, SchoolGirl.schoolCalls);
            FileOperations.writeToFile(schools, SchoolGirl.schools);
            channel.sendMessage(":white_check_mark: School removed :white_check_mark:").queue();

        }

    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

}
