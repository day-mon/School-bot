package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class RemoveProfessor extends Command {

    public RemoveProfessor() {
        super(new String[] { "removeprofessor", "profremove", "profrem" }, "RemoveProfessor");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();

        File professor = new File(
                "C:\\Users\\damon\\BotForSchool\\School-Bot\\schoolbot\\src\\main\\files\\professors.ser");

        if (args.length != 1) {

        } else {
            Professor empty = new Professor();
            for (Professor prof : SchoolGirl.professors) {
                if (prof.getLastName().equalsIgnoreCase(args[0])) {
                    prof = empty;
                }
            }

            if (empty.getLastName() == null) {
                MessageOperations.invalidUsageShortner("https:/", "Professor doesnt exist!", event.getMessage(), this);
            } else {
                SchoolGirl.professors.remove(empty);
                FileOperations.writeToFile(professor, SchoolGirl.professors);
                channel.sendMessage(":white_check_mark: Professor removed :white_check_mark:").queue();

            }

        }

    }

}
