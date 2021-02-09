package schoolbot.commands.school;

import java.util.HashMap;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.StudentImpl;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class RemoveAssignment extends Command {

    public RemoveAssignment() {
        super(new String[] {"removeassignment", "assignmentremove"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Message msg = event.getMessage();
        Command com = this;
        MessageChannel channel = event.getChannel();

        /**
         * Args[0] = "Class ID"
         * Args[1] = Assignment Name
         * 
         */

        String classID = args[0];
        boolean adminCheck = event.getMember().hasPermission(Permission.ADMINISTRATOR);
         
        
        if (adminCheck) {
            if (Ryan.classes.containsKey(classID)) {
                Classroom classToRemoveAssignmentFrom = Ryan.classes.get(classID);
                Professor classesProfessor = classToRemoveAssignmentFrom.getProfessor();
                    String assignmentName = args[1];
                    if (classToRemoveAssignmentFrom.getAssignments().containsKey(assignmentName)) {
                        Assignment assignemntToRemove = classToRemoveAssignmentFrom.getAssignments().get(assignmentName);
                        classToRemoveAssignmentFrom.getAssignments().remove(assignmentName);
                        classesProfessor.removeAssignment(assignemntToRemove);

                        FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
                        FileOperations.writeToFile(FileOperations.professor, Ryan.professors);

                        channel.sendMessage("Assignment removed!").queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", "Assignment doesnt exist!", msg, com);
                    }
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", "Class doesnt exist!", msg, com);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "You are not admin!", msg, com);
            }

         
    }
    
}
