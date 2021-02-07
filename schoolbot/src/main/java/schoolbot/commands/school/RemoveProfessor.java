package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class RemoveProfessor extends Command {

    public RemoveProfessor() {
        super(new String[] { "removeprofessor", "profremove", "profrem" });
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();

        Member userTyping = event.getMember();

        if (!userTyping.getPermissions().contains(Permission.ADMINISTRATOR)) {
            MessageOperations.invalidUsageShortner("https://google.com", "You don't have the wrong permissions!", msg, this);
            return;
        }
        

       
        /**
         * Had to add relative path back because if I didnt it wouldnt work for some
         * reason TODO: figure out why this doesnt work with a relative path!
         */

        /**
         * Args: [0] = Professor's Email suffix (String) Length of args need to be 1
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
            if (Ryan.professors.containsKey(args[0])) {
                String professorsEmailSuffix = args[0];
                Professor prof = Ryan.professors.get(professorsEmailSuffix);
                School profsSchool = prof.getProfessorsSchool();
                int numberOfClasses = prof.getProfessorsClasses().size();
                /**
                 * If the professor has no classes it is okay to delete
                 */
                if (numberOfClasses <= 0) {

                    Ryan.professors.remove(professorsEmailSuffix);
                    profsSchool.removeProfessor(prof);
                    FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
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
