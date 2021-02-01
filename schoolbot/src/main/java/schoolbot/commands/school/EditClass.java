package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;

public class EditClass extends Command {

    public EditClass() {
        super(new String[] { "editclass", "classedit" }, "EditClass");
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

        if (SchoolGirl.classes.containsKey(args[0])
                && SchoolGirl.classes.get(args[0]).getSchool() == SchoolGirl.schools.get(args[1])
                && SchoolGirl.schools.get(args[1]).getGuild() == event.getGuild()) {
            switch (args[2]) {
                case "classid":
                    SchoolGirl.classes.get(args[0]).setClassID(args[3]);
                    channel.sendMessage("Class ID sucessfully changed to: " + args[3]).queue();
                    break;
                case "time":
                    SchoolGirl.classes.get(args[0]).setTime(args[3]);
                    channel.sendMessage("Time sucessfully changed to: " + args[3]).queue();
                    break;
                case "year":
                    SchoolGirl.classes.get(args[0]).setYear(args[3]);
                    channel.sendMessage("Year sucessfully changed to: " + args[3]).queue();
                    break;
                case "name":
                    SchoolGirl.classes.get(args[0]).setClassName(args[3]);
                    channel.sendMessage("Class name sucessfully changed to: " + args[3]).queue();
                    break;
                case "credits":
                    boolean numeric = args[3].matches("-?\\d+(\\.\\d+)?");

                    if (numeric) {
                        SchoolGirl.classes.get(args[0]).setYear(args[3]);
                        channel.sendMessage("Credits sucessfully changed to: " + args[3]).queue();
                    } else {
                        // invalid usage
                    }
                    break;
                case "professor":
                    Professor prof = null;
                    for (Professor profs : SchoolGirl.professors) {
                        if (profs.getLastName().equalsIgnoreCase(args[3])) {
                            prof = profs;
                        }

                    }

                    if (prof != null) {
                        SchoolGirl.classes.get(args[0]).setProfessor(prof);
                        channel.sendMessage(
                                "Credits sucessfully changed to: " + prof.getLastName() + ", " + prof.getFirstName())
                                .queue();

                    } else {
                        // i nvalid usage prof doesnt exist
                    }

                    break;
                case "school":
                    if (SchoolGirl.schools.containsKey(args[0])) {
                        School school = SchoolGirl.schools.get(args[0]);
                        SchoolGirl.classes.get(args[0]).setSchool(school);
                        channel.sendMessage("School sucessfully changed to: " + school.getSchoolName()).queue();
                    } else {
                        // school doesnt exsit.
                    }
                    break;
                default:
                    // invalid usage.
            }
        }
    }

}
