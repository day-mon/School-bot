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
                "Correct usage: ++addprofessor **<professor name> <professor email>**", event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        GuildImpl guild = (GuildImpl) event.getGuild();

        if (args.length != 4) {
            MessageOperations.invalidUsageShortner("https://google.com", ":x: One or more of your args are missing :x:",
                    event.getMessage(), this);
        } else {
            if (SchoolGirl.schools.containsKey(args[3])) {
                School school = SchoolGirl.schools.get(args[3]);

                Professor prof = new Professor(guild, args[0], args[1], args[2], school);
                SchoolGirl.professors.add(prof);
                school.addProfessor(prof);

                File professor = new File(
                        "C:\\Users\\damon\\BotForSchool\\School-Bot\\schoolbot\\src\\main\\files\\professors.ser");

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
// TODO: do file
