package schoolbot.commands.school;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;
import schoolbot.natives.util.operations.StringOperations;

public class EditAssignment extends Command {

    public EditAssignment() {
        super(new String[] { "editassignment", "assignmentedit"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 2 arguments!", event.getMessage(), this);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();
        Command com = this;


        if (args.length > 2) {
            String classID = args[0];
            String assignmentName = args[1];
            if (Ryan.classes.containsKey(classID)) {
                Classroom clazzToEdit = Ryan.classes.get(classID);
                if (clazzToEdit.containsAssignment(assignmentName)) {
                    Assignment assignmentToEdit = clazzToEdit.getAssignments().get(assignmentName);
                    String attributeToEdit = args[2];

                    switch(attributeToEdit) {
                        case "professor":
                            String professorReference = args[3];
                            if (clazzToEdit.getSchool().getListOfProfessors().containsKey(professorReference)) {
                                Professor assignedProfessorChange = clazzToEdit.getSchool().getListOfProfessors().get(professorReference);
                                assignmentToEdit.setAssignedProfessor(assignedProfessorChange);
                                assignedProfessorChange.addAssignment(assignmentToEdit);
                                channel.sendMessage(":white_check_mark: Professor has been changed :white_check_mark:").queue();


                            } else {
                                String schoolName = clazzToEdit.getSchool().getSchoolName();
                                MessageOperations.invalidUsageShortner("https://google.com", "There is no professor that goes to " + schoolName + " with the reference " + professorReference, event.getMessage(), this);
                                return;
                            }
                            break;
                        case "name":
                            String newAssignmentName = args[3];
                            String oldAssignmentRef = assignmentToEdit.getAssignmentName();
                            Assignment newAssignment = assignmentToEdit;
                            newAssignment.setAssignmentName(newAssignmentName);
                            newAssignment.setAssignmentRef(newAssignmentName);

                            


                            /**
                             * I know this looks disgusting.. tell me about it :/
                             */
                            clazzToEdit.getAssignments().put(newAssignmentName, assignmentToEdit);
                            clazzToEdit.removeAssignment(oldAssignmentRef);

                            
                            
                            channel.sendMessage("Assignment name successfully changed to: " + newAssignmentName).queue();
                            break;
                        case "points":
                            boolean numeric = StringOperations.numericCheck(args[3]);
                            if (numeric) {
                                int newPoints = Integer.parseInt(args[3]);
                                assignmentToEdit.setPointsAmount((double)newPoints);
                                channel.sendMessage(assignmentToEdit.getAssignmentName() + " points successfully changed to: " + newPoints);
                            } else {
                                MessageOperations.invalidUsageShortner("https://google.com", args[3] + " is not a numeric value!", event.getMessage(), this);
                            }
                            break;
                        case "date":
                            Date date;
                            SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy hh:mm");

                            try {
                                date = formatter.parse(args[3]);
                                assignmentToEdit.setDueDate(date);
                                if (date.before(new Date())) assignmentToEdit.setExpired(false);
                                channel.sendMessage(assignmentToEdit.getAssignmentName() + " due date successfully changed to: " + date).queue();;
                            } catch (ParseException e) {
                                MessageOperations.invalidUsageShortner("https://google.com",
                                        "Could not parse date! Use this format next time (M/dd/yy hh:m)", event.getMessage(), this);
                                return;
                            }
                        break;
                            
                        default:
                            MessageOperations.invalidUsageShortner("https://google.com", attributeToEdit + " is not a valid entry!", event.getMessage(), this);
                    }


                    FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
                    FileOperations.writeToFile(FileOperations.classes, Ryan.classes);

                    
                }
            }
        } else {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 2 arguments! ", msg, com);
        }

    }
    
}
