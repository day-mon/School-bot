package schoolbot.commands.school;

import java.io.File;
import java.util.concurrent.ExecutorService;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.InvalidUsage;
import schoolbot.natives.util.MessageOperations;

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

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        File schools = new File("schoolbot\\src\\main\\files\\schools.ser");
        GuildImpl guild = (GuildImpl) event.getGuild();

        /**
         * Args: args[0] = ClassID ex. CS 0048 (String) args[1] = ClassNum ex. 20937
         * (String) args[2] = Time ex. MWF @ 12pm () args[3] = Year of coursE ex.
         * Sophmore (Year of the class) (String) args[4] = Credits ex. 3 (just the
         * credit amount of the class) args[5] = Professor ex. weierman (professor call
         * not the professors name) (String) args[6] = School (String)
         */

        /**
         * Check if the School and professor are first even valid entries.
         */

        if (args.length != 8) {
            MessageOperations.invalidUsageShortner("https://google.com", "Please check how many args you used!",
                    event.getMessage(), this);
        } else if (!Ryan.schools.containsKey(args[7])) {
            MessageOperations.invalidUsageShortner("https://google.com", "School doesnt exist", event.getMessage(),
                    this);
        } else if (Ryan.classes.containsKey(args[2])) {
            MessageOperations.invalidUsageShortner("https://google.com", "Class already exist!", event.getMessage(),
                    this);
        } else {
            // Regex matches to see if the 5th arg is a number
            boolean numeric = args[5].matches("-?\\d+(\\.\\d+)?");
            String creditsCheck = "";

            // Stores credits in variable
            int credits = 0;

            // Checking if professor is a valid professor
            if (Ryan.professors.containsKey(args[6])) {
                Professor profForClass = Ryan.professors.get(args[6]);

                // Checks again if its a numeric and then parses the string to an int
                if (numeric) {
                    credits = Integer.parseInt(args[5]);
                } else {
                    creditsCheck = ", you may have to reset your credits, the data you input for your credits was not a number!";
                    credits = 0;
                }

                // School to add the class too
                School schoolToAdd = Ryan.schools.get(args[7]);

                if (schoolToAdd.getListOfProfessors().containsKey(args[6])) {

                    Classroom classToAdd = new Classroom(guild, args[0], args[1], args[2], args[3], args[4], credits,
                            profForClass, schoolToAdd);
                    Ryan.classes.put(args[2], classToAdd);
                    schoolToAdd.addClazz(classToAdd);
                    FileOperations.writeToFile(schools, Ryan.schools);
                    channel.sendMessage(":white_check_mark: Class added sucesfully :white_check_mark:" + creditsCheck)
                            .queue();
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com",
                            "That professor does not goto this school!", event.getMessage(), this);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "Professor doesnt exist.",
                        event.getMessage(), this);
            }

        }

    }

}