package schoolbot.commands.school;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;

public class EditClass extends Command {

    public EditClass() {
        super(new String[] { "editclass", "classedit" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 3 arguments!", event.getMessage(), this);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
       MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();

        /**
         * First check if the school and the class even exist;
         */

        if (Ryan.classes.containsKey(args[0])) {
            Classroom classToEdit = Ryan.classes.get(args[0]);

            switch (args[1]) {
                case "classid":

                    classToEdit.setClassID(args[2]);
                    channel.sendMessage("Class ID sucessfully changed to: " + args[2]).queue();
                    break;
                case "time":
                    classToEdit.setTime(args[2]);
                    channel.sendMessage("Time sucessfully changed to: " + args[2]).queue();
                    break;
                case "name":
                    classToEdit.setClassName(args[2]);
                    channel.sendMessage("Class name sucessfully changed to: " + args[2]).queue();
                    break;
                case "credits":
                    boolean numeric = args[2].matches("-?\\d+(\\.\\d+)?");

                    if (numeric) {
                        int credits = Integer.parseInt(args[2]);
                        Ryan.classes.get(args[0]).setCredits(credits);
                        channel.sendMessage("Credits sucessfully changed to: " + args[2]).queue();
                    } else {
                        // invalid usage
                    }
                    break;
                case "professor":

                    if (Ryan.professors.containsKey(args[2])) {
                        Professor prof = Ryan.professors.get(args[2]);
                        classToEdit.setProfessor(prof);
                        channel.sendMessage(
                                "Credits sucessfully changed to: " + prof.getLastName() + ", " + prof.getFirstName())
                                .queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", "Professor doesnt exist!",
                                event.getMessage(), this);
                    }
                    break;
                case "channel":
                    boolean foundChannel = false;
                    String textChannel = args[2];
                    for (TextChannel channels : Ryan.jda.getTextChannels()) {
                        if (channels.toString() == textChannel) {
                            classToEdit.setTextChannel(channels.getName());
                            channel.sendMessage(textChannel + " successfully assigned to this class!").queue();
                            foundChannel = true;
                            return;
                        }
                    }
                    if (!foundChannel) MessageOperations.invalidUsageShortner("https://google.com", "That is not a valid text channel in this server!", msg, this);
                    break;
                case "channelID":
                boolean foundID = false;
                if (args[2].chars().allMatch(Character::isDigit)) {
                    long id = Long.parseLong(args[2]);
                for (TextChannel channels : Ryan.jda.getTextChannels()) {
                    if (channels.getIdLong() == id) {
                            classToEdit.setTextChannel(channel.getName());
                            classToEdit.setTextChannelID(id);
                            channel.sendMessage(id + " sucessfully assigned to this class!").queue();
                            foundID = true;
                            break;
                       }
                    }
                }
                if (!foundID) MessageOperations.invalidUsageShortner("https://google.com", "Channel id not found", event.getMessage(), this);
                break;
                case "role":
                String roleToAdd = args[2];
                boolean found = false;
                roleToAdd = roleToAdd.replaceAll("\\D+","");
                for (Role role : Ryan.jda.getRoles()) {
                    if (roleToAdd.equals(role.getId())) {
                        classToEdit.setRole(role);
                        channel.sendMessage("Role found... Setting role!").queue();
                        found = true;
                        break;
                    }
                }
               if(!found) channel.sendMessage("Role not found!").queue();
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

                        /**
                         * pitt --> 42;
                         * arr[hasOf(key)] = Univers;
                         */
                        boolean num = args[3].matches("-?\\d+(\\.\\d+)?");
                        if (num) {
                            int [] interval_1 = {Integer.parseInt(args[2])};
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
            FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
            FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
            FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
        }
    }

}
