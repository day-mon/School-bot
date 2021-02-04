package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolBot;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
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
        /**
         * Had to add relative path back because if I didnt it wouldnt work for some
         * reason TODO: figure out why this doesnt work with a relative path!
         */

        /**
         * Args: [0] = Professor's Last Name (String) Length of args need to be 1
         *
         * To remove a professor we must first: 1) Check to see if the professor they
         * input even exist. (We will do this by checking if the hashmap contains the
         * key of the professors last name) 2) Check to see if that professor has any
         * classes 2a) If they do throw a InvalidUsage 2b) If they dont remove them,
         * update ser file and send message
         * 
         */

        if (args.length == 1) {
            /**
             * Check to see if the professor exist
             */
            if (SchoolBot.professors.containsKey(args[0])) {
                Professor prof = SchoolBot.professors.get(args[0]);
                School profsSchool = prof.getProfessorsSchool();
                int numberOfClasses = prof.getProfessorsClasses().size();
                /**
                 * If the professor has no classes it is okay to delete
                 */
                if (numberOfClasses <= 0) {

                    SchoolBot.professors.remove(args[0]);
                    profsSchool.removeProfessor(prof);
                    FileOperations.writeToFile(professor, SchoolBot.professors);
                    channel.sendMessage(":white_check_mark: Professor is sucesfully deleted! :white_check_mark:")
                            .queue();
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com",
                            "You cannot delete a professor who has classes", event.getMessage(), this);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There is no professor with that last name", event.getMessage(), this);
            }
        }

    }
}
