package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.MessageOperations;

public class ListSchools extends Command {

    public ListSchools() {
        super(new String[] { "listschools", "schools" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        StringBuilder schools = new StringBuilder("```");

        for (String school : Ryan.schoolCalls) {
            schools.append(Ryan.schools.get(school).toString() + "\n" + "School refrence:  " + school + "\n\n");
        }

        schools.append("```");

        if (schools.toString().length() < 8)
            channel.sendMessage("**There are no schools at the moment...**").queue();
        else
            channel.sendMessage(schools).queue();
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in no arguments", event.getMessage(), this);

    }

}
