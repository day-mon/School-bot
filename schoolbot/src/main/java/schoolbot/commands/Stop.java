package schoolbot.commands;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.util.MessageOperations;
import schoolbot.natives.util.StringOperations;

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
