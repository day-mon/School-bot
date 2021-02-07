package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class RemoveClass extends Command {

    public RemoveClass() {
        super(new String[] { "removeclass", "classremove" });
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Message msg = event.getMessage();
        /**
         * Args[0] = school Args[1] = Class ID
         * 
         */

        Member userTyping = event.getMember();

        if (!userTyping.getPermissions().contains(Permission.ADMINISTRATOR)) {
            MessageOperations.invalidUsageShortner("https://google.com", "You don't have the wrong permissions!", msg,
                    this);
            return;
        }

        if (args.length > 1) {

        } else {
            if (Ryan.schools.containsKey(args[0])) {
                String schoolReefrence = args[0];
                String classID = args[1];

                School schoolToRemoveClassFrom = Ryan.schools.get(schoolReefrence);
                if (schoolToRemoveClassFrom.getListOfClasses().containsKey(classID)) {
                    Classroom classToRemove = schoolToRemoveClassFrom.getListOfClasses().get(classID);
                    int classSize = classToRemove.getClassList().size();
                    Professor classProfessor = classToRemove.getProfessor();
                    if (classSize <= 0) {
                        Ryan.classes.remove(classID);
                        classProfessor.removeClass(classToRemove);

                        FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
                        FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                        FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com",
                                "This class still has students in it!", event.getMessage(), this);
                    }
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "That school doesnt exist!",
                        event.getMessage(), this);
            }
        }
    }

}
