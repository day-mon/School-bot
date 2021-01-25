package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.util.MessageOperations;

public class JoinSchool extends Command {

    public JoinSchool() {
        super(new String [] {"joinschool", "schooljoin"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        User userTyping = event.getAuthor();
        GuildImpl guild = (GuildImpl)event.getGuild();
        MessageChannel channel = event.getChannel();

        if (args.length != 1) {
            System.out.println("lmao");

        } else {
            if (SchoolGirl.schools.containsKey(args[0])) {
                School school = SchoolGirl.schools.get(args[0]); 
                if (SchoolGirl.students.containsKey(userTyping)) {
                    Student student = SchoolGirl.students.get(userTyping);
                    if (student.getSchool() == null) {
                        student.setSchool(school);
                        school.addStudent(student);
                        channel.sendMessage("Sucessfully added student").queue();

                    } else {
                        String studentSchoolName = student.getSchool().getSchoolName();
                        MessageOperations.invalidUsageShortner("https://google.com", "You already goto: " + studentSchoolName, event.getMessage(), this);
                    }
                } else {
                    Student studentToAdd = new Student(guild, userTyping);
                    studentToAdd.setSchool(school);
                    school.addStudent(studentToAdd);
                    channel.sendMessage("Sucessfully added student" +
                                       " \n\t\t\t Some of your student settings are not configured please use the editself command to configure!").queue();
                }
                
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "School does not exist", event.getMessage(), this);
            }
        }
    }
    
}
