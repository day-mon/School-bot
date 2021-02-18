package schoolbot.commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.StringOperations;

public class Help extends Command {

    public Help() {
        super(new String[] { "help", "h" });
    }

    public Help(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // MessageChannel channel = event.getChannel();
        // Send generic help, we'll deal with later.
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        if (args.length >= 1) {
            String commandID = StringOperations.removeBounds(args[0]);
            for (Command com : Ryan.getCommands().values()) {
                if (com.isInCalls(commandID)) {
                    try {
                        channel.sendMessage(com.getDocumentation()).queue();
                    } catch (IllegalArgumentException e) {
                        if (e.toString().startsWith("java.lang.IllegalArgumentException: Provided emb")) {
                            channel.sendMessage(
                                    "There is no documentation for this command..\n**Contact  damon#9999 or Elsklivet#886**")
                                    .queue();
                        }
                    }
                }
            }
        }
    }

}
