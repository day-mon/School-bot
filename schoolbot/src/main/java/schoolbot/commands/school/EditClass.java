package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.MessageOperations;

public class EditClass extends Command {

    public EditClass() {
        super(new String[] { "editclass", "classedit" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();

        /**
         * First check if the school and the class even exist;
         */

        if (Ryan.classes.containsKey(args[0]) && Ryan.classes.get(args[0]).getSchool() == Ryan.schools.get(args[1])
                && Ryan.schools.get(args[1]).getGuild() == event.getGuild()) {
            Classroom classToEdit = Ryan.classes.get(0);

            switch (args[2]) {
                case "classid":

                    classToEdit.setClassID(args[3]);
                    channel.sendMessage("Class ID sucessfully changed to: " + args[3]).queue();
                    break;
                case "time":
                    classToEdit.setTime(args[3]);
                    channel.sendMessage("Time sucessfully changed to: " + args[3]).queue();
                    break;
                case "year":
                    classToEdit.setYear(args[3]);
                    channel.sendMessage("Year sucessfully changed to: " + args[3]).queue();
                    break;
                case "name":
                    classToEdit.setClassName(args[3]);
                    channel.sendMessage("Class name sucessfully changed to: " + args[3]).queue();
                    break;
                case "credits":
                    boolean numeric = args[3].matches("-?\\d+(\\.\\d+)?");

                    if (numeric) {
                        Ryan.classes.get(args[0]).setYear(args[3]);
                        channel.sendMessage("Credits sucessfully changed to: " + args[3]).queue();
                    } else {
                        // invalid usage
                    }
                    break;
                case "professor":

                    if (Ryan.professors.containsKey(args[3])) {
                        Professor prof = Ryan.professors.get(args[3]);
                        classToEdit.setProfessor(prof);
                        channel.sendMessage(
                                "Credits sucessfully changed to: " + prof.getLastName() + ", " + prof.getFirstName())
                                .queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", "Professor doesnt exist!",
                                event.getMessage(), this);
                    }
                    break;
                case "school":
                    if (Ryan.schools.containsKey(args[0])) {
                        School school = Ryan.schools.get(args[0]);
                        classToEdit.setSchool(school);
                        channel.sendMessage("School sucessfully changed to: " + school.getSchoolName()).queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", "School does not exist",
                                event.getMessage(), this);
                    }
                    break;
                default:
                    MessageOperations.invalidUsageShortner("https://google.com", args[2] + " is not a valid choice!",
                            event.getMessage(), this);
            }
        }
    }

}
