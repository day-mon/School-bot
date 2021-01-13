package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;
import schoolbot.natives.Student;

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

        if (args.length != 1) {

        } else {
            if (SchoolGirl.schools.containsKey(args[0])) {
                School school = SchoolGirl.schools.get(args[0]); 
                    
                
            } else {

            }
        }
    }
    
}
