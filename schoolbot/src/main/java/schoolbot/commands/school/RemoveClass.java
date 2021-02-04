package schoolbot.commands.school;

import java.io.File;

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
        /**
         * Args[0] = school Args[1] = Class ID
         * 
         */

        File professor = new File("schoolbot\\src\\main\\files\\professors.ser");
        File schools = new File("schoolbot\\src\\main\\files\\schools.ser");

        MessageChannel channel = event.getChannel();

        if (args.length > 1) {

        } else {
            if (Ryan.schools.containsKey(args[0])) {
                School schoolToRemoveClassFrom = Ryan.schools.get(args[0]);
                if (schoolToRemoveClassFrom.getListOfClasses().containsKey(args[1])) {
                    Classroom classToRemove = schoolToRemoveClassFrom.getListOfClasses().get(args[1]);
                    int classSize = classToRemove.getClassList().size();
                    Professor classProfessor = classToRemove.getProfessor();
                    if (classSize <= 0) {
                        Ryan.classes.remove(args[1]);
                        classProfessor.removeClass(classToRemove);

                        FileOperations.writeToFile(professor, Ryan.professors);
                        FileOperations.writeToFile(schools, Ryan.schools);
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
