package schoolbot.commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.MessageOperations;
import schoolbot.natives.util.operations.StringOperations;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;



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
        StringBuilder s = new StringBuilder();
        for (Command coms : Ryan.getCommands().values()) {
            s.append("`" + coms.getName() + "`, ");
        }
        s.deleteCharAt(s.length()-2);
        
        long time = System.currentTimeMillis() - Ryan.startTime;
        String uptime =  StringOperations.formatTime(time);

        Field field0 = new Field("Uptime", "`" + uptime + "`", true);
        Field field1 = new Field("Bot prefix", Ryan.PREFIX, true);
        Field field2 = new Field("Commands", s.toString(), true);
        Field fields [] = {field2, field1, field0};

        MessageOperations.embedAsMessage("Help", "https://google.com", fields, "Test", event.getMessage());
    
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
