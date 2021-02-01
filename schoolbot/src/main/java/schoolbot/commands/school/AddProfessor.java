package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class AddProfessor extends Command {

    public AddProfessor() {
        super(new String[] { "addprofessor", "addprof", "profadd" }, "AddProfessor");
    }

    public AddProfessor(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {

        MessageOperations.invalidUsageShortner("https://google.com",
                "Correct usage: ++addprofessor **\"<professor name>\" \"<professor email>\"**", event.getMessage(),
                this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        GuildImpl guild = (GuildImpl) event.getGuild();

        if (args.length < 3) {
            /** @TODO bad prac; specify arg with analyzer => todo new class, do all */
            MessageOperations.invalidUsageShortner("https://google.com", ":x: One or more of your args are missing :x:",
                    event.getMessage(), this);
        } else if (args.length == 3) {
            if (SchoolGirl.schools.containsKey(args[2])) {
                School school = SchoolGirl.schools.get(args[2]);

                String fName = (args[0].contains(" ") ? args[0].split(" ")[0] : "Professor");
                String lName = (args[0].contains(" ") ? args[0].split(" ")[1] : args[0]);
                fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
                lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);

                Professor prof = new Professor(guild, fName, lName, args[1], school);
                SchoolGirl.professors.add(prof);
                school.addProfessor(prof);

                File professor = new File("schoolbot\\src\\main\\files\\professors.ser");

                FileOperations.writeToFile(professor, SchoolGirl.professors);

                channel.sendMessage(":white_check_mark: Professor added succesfully :white_check_mark: ").queue();

            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "School doesnt exist", event.getMessage(),
                        this);

            }
        } else if (args.length >= 4) {
            if (SchoolGirl.schools.containsKey(args[3])) {
                School school = SchoolGirl.schools.get(args[3]);

                String fName = args[0];
                String lName = args[1];
                fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
                lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);

                Professor prof = new Professor(guild, fName, lName, args[2], school);
                SchoolGirl.professors.add(prof);
                school.addProfessor(prof);

                File professor = new File("schoolbot\\src\\main\\files\\professors.ser");

                FileOperations.writeToFile(professor, SchoolGirl.professors);

                channel.sendMessage(":white_check_mark: Professor added succesfully :white_check_mark: ").queue();
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "School doesnt exist", event.getMessage(),
                        this);

            }
        }
    }

    /*
     * Where args[0] = name & args[1] = prefix
     */

}
