package schoolbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.io.BufferedReader;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.*;

import schoolbot.commands.*;
import schoolbot.commands.school.AddSchool;
import schoolbot.commands.school.AddStudent;
import schoolbot.commands.school.EditClass;
import schoolbot.commands.school.ListClasses;
import schoolbot.commands.school.ListMajors;
import schoolbot.commands.school.ListProfessors;
import schoolbot.commands.school.AddClass;
import schoolbot.commands.school.AddProfessor;
import schoolbot.commands.school.ListSchools;
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
    public static ArrayList<String> schoolCalls = new ArrayList<String>();
    public static HashMap<String , School> schools = new HashMap<String, School>();
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Professor> professors = new ArrayList<>();
    public static HashMap<String, Classroom> classes  = new HashMap<>();
    public static TextChannel channel;


    public static void main(String[] args) throws LoginException {
        // if (args.length < 1) {
        //     System.out.println("You have to provide a token as first argument!");
        //     System.exit(1);
        // }
        
        String username = System.getProperty("user.name");
        String ian = "no <3";
    
        try {       
            BufferedReader fr = new BufferedReader(new FileReader( new File(username.charAt(0) != 'd' ?  "G:\\DiscordBots\\SchoolGirl\\schoolbot\\src\\main\\files\\token.txt" : "C:\\Users\\damon\\Desktop\\School-Bot-ReClone\\School-Bot\\schoolbot\\src\\main\\files\\token.txt")));
            ian = fr.readLine();
            // System.out.println(ian);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException iox){
            iox.printStackTrace();
        }

        // Commands initialization
        // Commands initialization; needs fixed. JDA threading is so remarkably ass-backwards that it can't initialize variables
        // in combination with threading. 
        commands = new HashMap<>();
        commands.put(new String[]{"ping", "p"}, new Ping()); // Ping
        commands.put(new String[]{"h","help"}, new Help());
        commands.put(new String[]{"wolf", "wolframe"}, new Wolfram());
        commands.put(new String[]{"addschool", "as"}, new AddSchool());
        commands.put(new String[]{"addstudent"}, new AddStudent());
        commands.put(new String[]{"listmajors", "majors"}, new ListMajors());
        commands.put(new String[]{"listschools", "schools"}, new ListSchools());
        commands.put(new String[]{"addprofessor", "addprof", "profadd"}, new AddProfessor());
        commands.put(new String[]{"addclass"}, new AddClass());
        commands.put(new String[]{"listprofessors", "listprofs"}, new ListProfessors()); 
        commands.put(new String[]{"editclass", "classedit"}, new EditClass());
        commands.put(new String[]{"classes", "listclasses"}, new ListClasses());
        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        JDABuilder.createLight(ian, EnumSet.allOf(GatewayIntent.class)) // <- "allOf(GI.class)" => The method allOf(Class<E>) in the type EnumSet is not applicable for the arguments (Class<GatewayIntent>) Java(67108979)
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
        if(!msg.getContentRaw().startsWith(PREFIX)) return;
        String comCall = StringOperations.removePrefix(msg.getContentRaw());
        String[] callAndFlags = comCall.split(" ",2);
        String[] comParts = comCall.split(" ");
        String[] flags = (callAndFlags.length > 1) ? StringOperations.parseArgs(callAndFlags[1]) : null;
        System.out.println("FLAGS WE SHOULD SEND"+Arrays.toString(flags));
        for (Command com : commands.values()) {
            if(com.isInCalls(comParts[0])){
                if(flags != null){
                    com.run(event, flags);
                }else{
                    com.run(event);
                }
            }
        }
    }

    public static HashMap<String[], ? extends Command> getCommands(){
        return commands;
    }

}