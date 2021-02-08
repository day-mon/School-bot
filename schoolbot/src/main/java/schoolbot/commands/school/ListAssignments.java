package schoolbot.commands.school;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.commands.Command;

public class ListAssignments extends Command {

    public ListAssignments() {
        super(new String[] { "listassignments", "assignments" });
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Member userTyping = event.getMember();
        boolean adminCheck = userTyping.hasPermission(Permission.ADMINISTRATOR);

        if ()


    }
    
}
