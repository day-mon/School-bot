package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.Majors;

public class AddStudent extends Command {

    public AddStudent() {
        super(new String[] { "addstudent" });
    }

    public AddStudent(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        File students = new File("schoolbot\\src\\main\\files\\students.ser");
        GuildImpl guild = (GuildImpl) event.getGuild();
        /**
         * args[0] = Real Name args[1] = User args[2] = School args[3] = Major args[4] =
         * GPA
         * 
         * 
         */

        if (args.length != 5) {

        } else {
            if (SchoolGirl.schools.containsKey(args[2])) {
                School school = SchoolGirl.schools.get(args[2]);
                double num = 0.0;
                Majors major = Majors.UNDECIDED;
                User studentToAddUsr = event.getMessage().getMentionedUsers().get(0);

                try {
                    num = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {
                }
                ;

                args[3] = args[3].toUpperCase().replace(" ", "_");
                for (Majors majors : Majors.values()) {
                    if (args[3].equals(majors.toString())) {
                        major = majors;
                        break;
                    }
                }

                Student studentToAdd = new Student(guild, studentToAddUsr, school, num, new Majors[] { major },
                        args[0]);
                school.addStudent(studentToAdd);
                FileOperations.writeToFile(students, SchoolGirl.students);
                channel.sendMessage("Student " + studentToAddUsr.getAsMention() + " sucesfully added!").queue();

            } else {
                System.out.println("no school!");
                // invalid usage goes here
            }
        }

    }

}
