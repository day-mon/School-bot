package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolBot;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;

public class JoinSchool extends Command {

    public JoinSchool() {
        super(new String[] { "joinschool", "schooljoin" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        User userTyping = event.getAuthor();
        GuildImpl guild = (GuildImpl) event.getGuild();
        MessageChannel channel = event.getChannel();
        File students = new File("schoolbot\\src\\main\\files\\students.ser");

        if (args.length != 1) {
            System.out.println("lmao");

        } else {
            if (SchoolBot.schools.containsKey(args[0])) {
                // Grabs the school if it exist
                School school = SchoolBot.schools.get(args[0]);

                if (SchoolBot.students.containsKey(userTyping)) {
                    // Grabs student if they already exist
                    Student student = SchoolBot.students.get(userTyping);

                    if (student.getSchool() == null) {

                        // Writing to students.ser
                        FileOperations.writeToFile(students, SchoolBot.students);

                        // Setting & adding
                        student.setSchool(school);
                        school.addStudent(student);
                        SchoolBot.students.put(event.getAuthor(), student);

                        // Sucess message
                        channel.sendMessage("Sucessfully added student").queue();

                    } else {
                        // Error message for when student already belongs to a school
                        String studentSchoolName = student.getSchool().getSchoolName();
                        MessageOperations.invalidUsageShortner("https://google.com",
                                "You already goto: " + studentSchoolName, event.getMessage(), this);
                    }
                } else {
                    // Declaring
                    Student studentToAdd = new Student(guild, userTyping);

                    // Setting and adding student
                    studentToAdd.setSchool(school);
                    school.addStudent(studentToAdd);
                    SchoolBot.students.put(event.getAuthor(), studentToAdd);

                    // Writing to students to file
                    FileOperations.writeToFile(students, SchoolBot.students);

                    // Sucess message
                    channel.sendMessage("Sucessfully added student"
                            + " \n\t\t\t Some of your student settings are not configured please use the editself command to configure!")
                            .queue();
                }

            } else {
                // Error Message for school not existing
                MessageOperations.invalidUsageShortner("https://google.com", "School does not exist",
                        event.getMessage(), this);
            }
        }
    }

}
