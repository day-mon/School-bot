package schoolbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.MessageOperations;

public class Stop extends Command {

    public Stop() {
        super(new String[] { "stop", "exit" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        Member userTyping = event.getMember();
        MessageChannel channel = event.getChannel();

        if (userTyping.hasPermission(Permission.ADMINISTRATOR)) {
            channel.sendMessage("Shuting down.. Goodbye now!").queue();
            Ryan.jda.shutdown();
        }

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Message msg = event.getMessage();
        Command com = this;
        
        MessageOperations.invalidUsageShortner("https://google.com", "Bruh what are you doing...", msg, com);
        return;
    }
    
}
