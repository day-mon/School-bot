package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.Ryan;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;

public class AddClass extends Command {

    /**
     * Adding a class will require a professor, credits, year, and a school that
     * school will have to match the professor
     */

    public AddClass() {
        super(new String[] { "addclass" });
    }

    public AddClass(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "You have entered no arguments!",
                event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();

        GuildImpl guild = (GuildImpl) event.getGuild();

        /**
         * Check if the School and professor are first even valid entries.
         */

        if (args.length != 6) {
            MessageOperations.invalidUsageShortner("https://google.com", "Please check how many args you used!",
                    event.getMessage(), this);
        } else if (Ryan.classes.containsKey(args[1])) {
            MessageOperations.invalidUsageShortner("https://google.com", "Class already exist!", event.getMessage(),
                    this);
        } else {

            // Regex matches to see if the 5th arg is a number
            boolean numeric = args[4].matches("-?\\d+(\\.\\d+)?");
            String creditsCheck = "";

            // Stores credits in variable
            int credits = 0;


            String professorReference = args[5];

            // Checking if professor is a valid professor
            if (Ryan.professors.containsKey(professorReference)) {
                Professor profForClass = Ryan.professors.get(professorReference);

                // Checks again if its a numeric and then parses the string to an int
                if (numeric) {
                    credits = Integer.parseInt(args[4]);
                } else {
                    creditsCheck = ", you may have to reset your credits, the data you input for your credits was not a number!";
                    credits = 0;
                }

                // School to add the class too
                School schoolToAdd = profForClass.getProfessorsSchool();

                String className = args[0];
                String classID = args[1];
                String classNum = args[2];
                String time = args[3];
                String textChannel = channel.getName();
                String [] channelParsed = textChannel.split("\\-");
                long channelID = channel.getIdLong();


  

                /**
                 * Making the classroom object to add to hashmaps
                 */

                Classroom classToAdd = new Classroom(guild, className, classID, classNum, time, credits, profForClass,
                        schoolToAdd, textChannel, channelID);

                /**
                 * Adding to HashMaps
                 */

      
                
                for (Role roles : Ryan.jda.getRoles()) {
                    String [] roleSplit = roles.getName().contains("-") ? roles.getName().split("-") : roles.getName().split("\\s");
                    if (roleSplit[roleSplit.length-1].equals(channelParsed[channelParsed.length-1])) {
                        classToAdd.setRole(roles);
                        break;
                    }
                }

                Ryan.classes.put(classNum, classToAdd);
                schoolToAdd.addClazz(classToAdd);
                profForClass.addClass(classToAdd);
                Ryan.schools.get(schoolToAdd.getSchoolreference()).addClazz(classToAdd);
                Ryan.professors.get(professorReference).addClass(classToAdd);



                /**
                 * File writing
                 */
                FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
                FileOperations.writeToFile(FileOperations.professor, Ryan.professors);

                String roleAssigned = classToAdd.getRole() == null ? "None!" : classToAdd.getRole().getAsMention();

                channel.sendMessage(":white_check_mark: Class added sucesfully \n Role assigned: " + roleAssigned + "\n Text channel assigned: #" + classToAdd.getTextChannel() + ":white_check_mark:" + creditsCheck)
                        .queue();

            } else {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "That professor does not goto this school!", event.getMessage(), this);
            }
        }

    }

}
