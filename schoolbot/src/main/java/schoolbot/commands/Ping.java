package schoolbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.natives.util.StringOperations;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Ping extends Command {

    public Ping(){
        super(new String[]{"ping","p"});
        this.documentation = StringOperations.parseDoc("schoolbot\\docs\\Ping.txt");
    }

    public Ping(String[] aliases){
        super(aliases);
        this.documentation = StringOperations.parseDoc("schoolbot\\docs\\Ping.txt");
    }
    
    @Override
    public void run(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!")
                   .queue(response -> {
                       response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
            channel.sendMessage(getDocumentation()).queue();
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!") 
               .queue(response -> {
                   response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });
    }



}
