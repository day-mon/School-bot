package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.Message;
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
        Message msg = event.getMessage();

        /**
         * First check if the school and the class even exist;
         */

        if (Ryan.classes.containsKey(args[0]) && Ryan.classes.get(args[0]).getSchool() == Ryan.schools.get(args[1])
                && Ryan.schools.get(args[1]).getGuild() == event.getGuild()) {
            Classroom classToEdit = Ryan.classes.get(args[0]);

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
                        MessageOperations.invalidUsageShortner("https://google.com", "School does not exist",event.getMessage(), this);
                    }
                    break;
                case "intervals":
                /**
                 * args[3] = interval 1
                 * args[4] = interval 2
                 * args[5] = interval 3
                 */
                int size = 2 - args.length;

                switch(size) {
                    case 1:
                        // 1 interval
                        boolean num = args[3].matches("-?\\d+(\\.\\d+)?");
                        if (num) {
                            int [] interval_1 = {Integer.parseInt(args[3])};
                            classToEdit.setIntervals(interval_1);
                            channel.sendMessage("Sucessfully updated intervals!");
                        } else {
                            MessageOperations.invalidUsageShortner("https://google.com", args[3] + " is not a number!", msg, this);
                        }
                    break;
                    case 2:
                        boolean interv_1 = args[3].matches("-?\\d+(\\.\\d+)?");
                        boolean interv_2 = args[4].matches("-?\\d+(\\.\\d+)?");

                        if (interv_1 && interv_2) {
                            int interval_1 = Integer.parseInt(args[3]);
                            int interval_2 = Integer.parseInt(args[4]);
                            int [] intervals_2 = {interval_1, interval_2};
                            classToEdit.setIntervals(intervals_2);  
                            channel.sendMessage("Sucessfully updated intervals!");
                        }

                    break;
                    case 3:
                        boolean interval_1 = args[3].matches("-?\\d+(\\.\\d+)?");
                        boolean interval_2 = args[4].matches("-?\\d+(\\.\\d+)?");
                        boolean interval_3 = args[5].matches("-?\\d+(\\.\\d+)?");

                        if (interval_1 && interval_2 && interval_3) {
                            int interva_1 = Integer.parseInt(args[3]);
                            int interva_2 = Integer.parseInt(args[4]);
                            int interva_3 = Integer.parseInt(args[5]);


                            int [] intervals_3 = {interva_1, interva_2, interva_3};
                            classToEdit.setIntervals(intervals_3);  
                            channel.sendMessage("Sucessfully updated intervals!");
                        }
                        break;
                }

                default:
                    MessageOperations.invalidUsageShortner("https://google.com", args[2] + " is not a valid choice!",event.getMessage(), this);
            }
        }
    }

}
