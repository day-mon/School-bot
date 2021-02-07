package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.Majors;
import schoolbot.natives.util.MessageOperations;

public class AddStudent extends Command {

    public AddStudent() {
        super(new String[] { "addstudent" });
    }

    public AddStudent(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        int amountOfArgs = args.length;



        if (amountOfArgs != 5) {
            MessageOperations.invalidUsageShortner("https://google.com", "You used ("+ amountOfArgs+ ") argument(s) , this command takes in 5 arguments!", event.getMessage(), this);
        } else {
            if (Ryan.schools.containsKey(args[2])) {
                School school = Ryan.schools.get(args[2]);
                double num = 0.0;
                Majors major = Majors.UNDECIDED;
                User studentToAddUsr = event.getMessage().getMentionedUsers().get(0);

                try {
                    num = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {};

                args[3] = args[3].toUpperCase().replace(" ", "_");
                for (Majors majors : Majors.values()) {
                    if (args[3].equals(majors.toString())) {
                        major = majors;
                        break;
                    }
                }

                Student studentToAdd = new Student(studentToAddUsr, school, num, new Majors[] { major },
                        args[0]);
                school.addStudent(studentToAdd);

                /**
                 * Writes to schools.ser and students.ser because we add a student to the school
                 * and we add a new student
                 */
                FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                FileOperations.writeToFile(FileOperations.students, Ryan.students);

                channel.sendMessage("Student " + studentToAddUsr.getAsMention() + " sucesfully added!").queue();

            } else {
                MessageOperations.invalidUsageShortner("https://google.com", args[2] + " is not a valid school reference", event.getMessage(), this);
            }
        }

    }

}
