package schoolbot.commands.school;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;
import schoolbot.natives.util.StringOperations;

public class AddAssignment extends Command {

    public AddAssignment() {
        super(new String[] { "addassignment" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // new invalid usage

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        /**
         * args[0] = class id args[1] = assignment name args[2] = due date (must be in
         * yyyy-MM-dd format) args[3] = points possible args[4] = assignment type
         */

        MessageChannel channel = event.getChannel();
        SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy hh:mm");
        Date date = new Date();

        if (args.length < 5) {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 5 args!",
                    event.getMessage(), this);
        } else {
            if (Ryan.classes.containsKey(args[0])) {
                Classroom classToAddAnAssignmentTo = Ryan.classes.get(args[0]);
                Professor professorInClass = classToAddAnAssignmentTo.getProfessor();
                String assignmentName = args[1];
                double pointsPossible = 0.0;
                String assignmentType = args[4];


                if (classToAddAnAssignmentTo.containsAssignment(assignmentName)) {
                    MessageOperations.invalidUsageShortner("https://google.com", "This assignment already exist!", event.getMessage(), this);
                    return;
                }

                /**
                 * Attempting to parse date, if date cannot be parsed throw a invalid usage.
                 */
                try {
                    date = formatter.parse(args[2]);
                } catch (ParseException e) {
                    MessageOperations.invalidUsageShortner("https://google.com",
                            "Could not parse date! Use this format next time (M/dd/yy hh:m)", event.getMessage(), this);
                    return;
                }

                boolean numeric = StringOperations.numericCheck(args[3]);

                if (numeric) {
                    pointsPossible = Integer.parseInt(args[3]);
                    pointsPossible = (double)pointsPossible;
                }

                Assignment assignmentToCreate = new Assignment(classToAddAnAssignmentTo, assignmentName, date,
                        pointsPossible, assignmentType);

                // File Writing and saving to HashMaps
                professorInClass.addAssignment(assignmentToCreate);
                //classToAddAnAssignmentTo.addToAllStudents(assignmentToCreate);
                classToAddAnAssignmentTo.addAssignment(assignmentToCreate);

                FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
                FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
                channel.sendMessage(":white_check_mark: Assignment added :white_check_mark:").queue();
                long timeDue = (date.getTime() / 1000) - (System.currentTimeMillis() / 1000);
                channel.sendMessage(classToAddAnAssignmentTo.getRole() == null ? "" : classToAddAnAssignmentTo.getRole().getAsMention() + " " + assignmentName + " is due in " + StringOperations.formatTime(timeDue)).queue();

            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "Class doesnt exist", event.getMessage(),
                        this);
            }
        }

    }

}
