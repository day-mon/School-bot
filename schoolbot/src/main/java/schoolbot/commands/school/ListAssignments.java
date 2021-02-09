package schoolbot.commands.school;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;

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

        if (adminCheck || 1 == 1) {
            if (args.length == 2) {
                String schoolKey = args[0];
                String classId = args[1];
                School s = Ryan.schools.get(schoolKey);
                Classroom c = s.getListOfClasses().get(classId);
                String print = "";
                String chan = "bot-test-grounds";
                for (Assignment a : c.getAssignments().values()) {
                    print += a.toString() + "\n";
                }

                if (!print.equals("")) {
                    Ryan.jda.getTextChannelsByName(chan, true).get(0).sendMessage(print);
                } else {
                    // "There are currently no assignments for this class"
                }
            } else {
                // invalid number of args
            }

        }

    }

}
