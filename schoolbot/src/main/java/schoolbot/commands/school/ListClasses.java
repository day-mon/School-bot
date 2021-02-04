package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;
import schoolbot.natives.util.MessageOperations;

public class ListClasses extends Command {

    public ListClasses() {
        super(new String[] { "classes", "listclasses" });
    }

    public ListClasses(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        System.out.println("School doesnt exit");

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

        MessageChannel channel = event.getChannel();
        /**
         * To avoid spaming the chat we will only ask for classes that are from a
         * specific school, subject or professor args[0] = school args[1] (optional) =
         * subject / professor
         */

        if (args.length == 1) { // check lengths first
            if (Ryan.schools.containsKey(args[0])) {
                School schoolToGetClasses = Ryan.schools.get(args[0]);
                int schoolsClasses = schoolToGetClasses.getListOfClasses().size();
                if (schoolsClasses > 0) {
                    StringBuilder classes = new StringBuilder("");
                    classes.append("Classes at: ***" + schoolToGetClasses.getSchoolName() + "*** \n");
                    classes.append("```");
                    for (Classroom clazz : schoolToGetClasses.getListOfClasses().values()) {
                        classes.append(clazz.toString() + "\n");

                        if (classes.length() >= 1750) {
                            MessageOperations.messageExtender(classes, channel);
                        }
                    }

                    classes.append("```");
                    channel.sendMessage(classes).queue();
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", "There are no classes at this school!",
                            event.getMessage(), this);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "School does not exist!",
                        event.getMessage(), this);
            }
        }

    }
}
