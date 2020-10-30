package schoolbot.commands;

<<<<<<< HEAD
import java.util.HashMap;
=======
import java.util.Arrays;
>>>>>>> 2b8ac101495b1f0fc8867dd52170df326edced33

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
        
        System.out.println(Arrays.toString(args));

        if (args.length < 1) {
            
        new InvalidUsage("n.com", "n", "n", event.getMessage());
        } else {
            args[0] = args[0].replaceAll("%", "%25")
                .replaceAll("\\+", "%2B")
                .replaceAll("\\s", "+")
                .replaceAll("\\$", "%20")
                .replaceAll("&", "%26")
                .replaceAll("\\^", "%5E")
                .replaceAll("`", "%60")
                .replaceAll("\\/", "%2F")
                .replaceAll("'", "%27")
                .replaceAll("\\{", "%7B")
                .replaceAll("\\}", "%7D")
                .replaceAll(",", "%2C")
                .replaceAll("\\\"", "%22")
                .replaceAll("[“]", "%22")
                .replaceAll("[”]", "%22")
                .replaceAll("\\|", "%7C")
                .replaceAll("=", "%3D")
                .replaceAll(":", "%3A")
                .replaceAll("<", "%3C")
                .replaceAll("\\(", "%28")
                .replaceAll("\\)", "%29")
                .replaceAll(">", "%3E")
                .replaceAll(";", "%3B")
                .replaceAll("~", "%7E")
                .replaceAll("@", "%40");
            
            channel.sendMessage("https://www.wolframalpha.com/input/?i=" + args[0]).queue();

        }

    }
    
}
