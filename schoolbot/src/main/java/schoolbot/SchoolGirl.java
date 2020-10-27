package schoolbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import java.util.EnumSet;
import java.io.BufferedReader;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.*;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.annotations.*;
import net.dv8tion.jda.api.requests.*;
import net.dv8tion.jda.api.events.channel.text.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.*;


/** 
 * Alias: Joshigakusei, only by Elsklivet. :}
 */
public class SchoolGirl extends ListenerAdapter {

    private final static String PREFIX = "++";
    private final static String gavinID = "348235152972972042";
    private final static String damonID = "105141507996061696";



    public static void main(String[] args) throws LoginException {
        // if (args.length < 1) {
        //     System.out.println("You have to provide a token as first argument!");
        //     System.exit(1);
        // }
        
        String username = System.getProperty("user.name");
        String cum = "fuck off";


    
        try {       
            BufferedReader fr = new BufferedReader(new FileReader( new File(username.charAt(0) < 'd' ?  "schoolbot\\src\\main\\files\\token.txt" : "School-Bot\\schoolbot\\src\\main\\files\\token.txt")));
            cum = fr.readLine();
            System.out.println(cum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException iox){
            iox.printStackTrace();
        }

        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        JDABuilder.createLight(cum, EnumSet.allOf(GatewayIntent.class))
            .addEventListeners(new SchoolGirl())
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("with school textbooks"))
            .build();
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals(PREFIX+"ping"))
        {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                   .queue(response -> {
                       response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        }
    }
}