package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.MessageOperations;

public class ListProfessors extends Command {

    public ListProfessors() {
        super(new String[] { "listprofessors", "listprofs" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        // TODO Auto-generated method stub
        if (args.length != 1) {
            MessageOperations.invalidUsageShortner("https://google.com",
                "Correct usage: ++listprofessors **\"<school name>\"**", event.getMessage(), this);
        } else if (!SchoolGirl.schools.containsKey(args[0])) {
            MessageOperations.invalidUsageShortner("https://google.com", "School doesnt exist", event.getMessage(),
            this);
        } else {
            StringBuilder professors = new StringBuilder("```");
            School school = SchoolGirl.schools.get(args[0]);
            for (Professor prof : school.getListOfProfessors().values()) {
                professors.append(prof.toString());

                if (professors.length() >= 1750) {
                    MessageOperations.messageExtender(professors, channel);
                }
            }

            /**
             * Problems could arise when the message is longer than 2000 characters.
             */

            professors.append("```");
            if (professors.toString().length() < 8)
                channel.sendMessage("**There are no professors for this school at the moment...**").queue();
            else
                channel.sendMessage(professors).queue();

        }

    }
}
