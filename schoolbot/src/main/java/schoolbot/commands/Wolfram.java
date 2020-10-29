package schoolbot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.natives.util.InvalidUsage;
import schoolbot.natives.util.StringOperations;

public class Wolfram extends Command {

    public Wolfram() {
        super(new String[]{"wolf", "wolframe", "wf"});
    }

    public Wolfram(String[] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        
        

        if (args.length < 1) {
            
        new InvalidUsage("n.com", "n", "n", event.getMessage());
        } else {
            channel.sendMessage("https://www.wolframalpha.com/input/?i=" + args[0]).queue();

        }

    }
    
}
