package schoolbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.io.BufferedReader;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.*;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.annotations.*;
import net.dv8tion.jda.api.requests.*;
import net.dv8tion.jda.api.events.channel.text.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.*;

import schoolbot.commands.*;
import schoolbot.natives.*;
import schoolbot.natives.util.*;


/** 
 * Alias: Joshigakusei, only by Elsklivet. :}
 */
public class SchoolGirl extends ListenerAdapter {

    public final static String PREFIX = "++";
    private final static String gavinID = "348235152972972042";
    private final static String damonID = "105141507996061696";
    private static HashMap<String[], Command> commands; // we'll do the init for this later on line 64

    public static TextChannel channel;


    public static void main(String[] args) throws LoginException {
        // if (args.length < 1) {
        //     System.out.println("You have to provide a token as first argument!");
        //     System.exit(1);
        // }
        
        String username = System.getProperty("user.name");
        String ian = "no <3";


    
        try {       
            BufferedReader fr = new BufferedReader(new FileReader( new File(username.charAt(0) < 'd' ?  "schoolbot\\src\\main\\files\\token.txt" : "School-Bot\\schoolbot\\src\\main\\files\\token.txt")));
            ian = fr.readLine();
            System.out.println(ian);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException iox){
            iox.printStackTrace();
        }

        // Commands initialization
        // commands.put(new String[]{"ping", "p"}, new Ping()); // Ping

        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        JDABuilder.createLight(ian, EnumSet.allOf(GatewayIntent.class))
            .addEventListeners(new SchoolGirl())
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("with school textbooks"))
            .build();
    }

    /*
    onGuildMessage:
        hashmap get command by id:
            command.run
    */

    /**
     * Intilizies a text channel so we dont have to grab in every file.
     * @param tc
     */

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        String comCall = StringOperations.removePrefix(msg.getContentRaw());
        String[] comParts = comCall.split(" ");
        String[] flags = null;
        for (Command com : commands.values()) {
            if(com.isInCalls(comParts[0])){
                if(comParts.length > 1){
                    flags = Arrays.copyOfRange(comParts, 1, comParts.length);
                    com.run(event, flags);
                }else{
                    com.run(event);
                }
            }
        }
        // Old code
        /*
        if (msg.getContentRaw().startsWith(PREFIX+"ping"))
        {
            Command cmd = new Ping();
            cmd.run(event);
        }
        */
    }

}