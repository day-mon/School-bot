package schoolbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import schoolbot.commands.Clear;
import schoolbot.commands.Help;
import schoolbot.commands.ListCommands;
import schoolbot.commands.Ping;
import schoolbot.commands.Wolfram;
import schoolbot.commands.school.AddAssignment;
import schoolbot.commands.school.AddClass;
import schoolbot.commands.school.AddProfessor;
import schoolbot.commands.school.AddSchool;
import schoolbot.commands.school.EditAssignment;
import schoolbot.commands.school.EditClass;
import schoolbot.commands.school.EditProfessor;
import schoolbot.commands.school.EditSchool;
import schoolbot.commands.school.LinearAlgebra;
import schoolbot.commands.school.ListAssignments;
import schoolbot.commands.school.ListClasses;
import schoolbot.commands.school.ListMajors;
import schoolbot.commands.school.ListProfessors;
import schoolbot.commands.school.ListSchools;
import schoolbot.commands.school.RemoveAssignment;
import schoolbot.commands.school.RemoveClass;
import schoolbot.commands.school.RemoveProfessor;
import schoolbot.commands.school.RemoveSchool;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.Event;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.StringOperations;
import schoolbot.natives.util.threading.RyanCoolThread;
import schoolbot.natives.util.threading.RyanThread;

public class Ryan extends ListenerAdapter {

	public final static String PREFIX = "++";
	/**
	 * HashMpas
	 */
	private static HashMap<String[], Command> commands; 
	private static HashMap<String[], Event> events;
	public static ArrayList<String> schoolCalls = new ArrayList<String>();
	public static HashMap<String, School> schools = new HashMap<String, School>();
	public static HashMap<String, Professor> professors = new HashMap<>();
	public static HashMap<String, Classroom> classes = new HashMap<>();
	public static HashMap<User, Boolean> memify = new HashMap<>();


	/**
	 * Varibles for thread
	 */
	public static long startTime = System.currentTimeMillis();
	public static final long interval = 900; // 15 minutes
	public static final long onehour = interval * 4;
	public static LocalDateTime today = LocalDateTime.now();
	public static TextChannel tc;
	public static HashMap<Classroom, TextChannel> alert = new HashMap<Classroom, TextChannel>();

	/**
	 * Other
	 */
	public static JDA jda;
	public static int GUNGACOUNT;
	public static LinkedHashMap<User, Date> lastMessages = new LinkedHashMap<>();

	public static void main(String[] args)
			throws LoginException, ClassNotFoundException, InterruptedException, IOException {

		String token = "N/A";

		

		FileOperations.loadFiles();
	

		try {
			BufferedReader fr = new BufferedReader(new FileReader(new File("schoolbot\\src\\main\\files\\token.txt")));
			token = fr.readLine();
		} catch (IOException e) {
		}
		;
		// Commands initialization
		// Commands initialization; needs fixed. JDA threading is so remarkably
		// ass-backwards that it can't initialize variables
		// in combination with threading.
		commands = new HashMap<>();
		commands.put(new String[] { "ping", "p" }, new Ping()); // Ping
		commands.put(new String[] { "h", "help" }, new Help());
		commands.put(new String[] { "wolf", "wolframe" }, new Wolfram());
		commands.put(new String[] { "addschool", "as" }, new AddSchool());
		commands.put(new String[] { "listmajors", "majors" }, new ListMajors());
		commands.put(new String[] { "listschools", "schools" }, new ListSchools());
		commands.put(new String[] { "addprofessor", "addprof", "profadd" }, new AddProfessor());
		commands.put(new String[] { "addclass" }, new AddClass());
		commands.put(new String[] { "listprofessors", "listprofs" }, new ListProfessors());
		commands.put(new String[] { "editclass", "classedit" }, new EditClass());
		commands.put(new String[] { "classes", "listclasses" }, new ListClasses());
		commands.put(new String[] { "removeclass", "classremove" }, new RemoveClass());
		commands.put(new String[] { "editprofessor", "profedit"}, new EditProfessor());
		commands.put(new String[] { "removeschool", "schoolremove", "rschool" }, new RemoveSchool());
		commands.put(new String[] { "removeprofessor", "profremove", "profrem" }, new RemoveProfessor());
		commands.put(new String[] { "addassignment" }, new AddAssignment());
		commands.put(new String[] { "purge", "clear" }, new Clear());
		commands.put(new String[] { "editassignment", "assignmentedit" }, new EditAssignment());
		commands.put(new String[] { "removeassignment", "assignmentremove" }, new RemoveAssignment());
		commands.put(new String[] { "linearalgebra", "la" }, new LinearAlgebra());
		commands.put(new String[] { "listassignments", "assignments" }, new ListAssignments());
		commands.put(new String[] { "listcommands", "commands", "coms", "listcoms" }, new ListCommands());
		commands.put(new String [] {"editschool", "schooledit", "schoole"}, new EditSchool());

		// args[0] should be the token
		// We only need 2 intents in this bot. We only respond to messages in guilds and
		// private channels.
		// All other events will be disabled.
		jda = JDABuilder.createLight(token, EnumSet.allOf(GatewayIntent.class)) // <- "allOf(GI.class)" => The method
				.addEventListeners(new Ryan()).setStatus(OnlineStatus.DO_NOT_DISTURB)
				.setActivity(Activity.playing("type ++help for help!")).build();
		jda.awaitReady();

		RyanThread ryanThread = new RyanThread();
		Thread assignmentAlert = new Thread(ryanThread);
		assignmentAlert.start();

		RyanCoolThread ryanCoolThread = new RyanCoolThread();
		Thread classAlert = new Thread(ryanCoolThread);
		classAlert.start();


	}

	public static long timeNow() {
		return System.currentTimeMillis();
	}

	/*
	 * onGuildMessage: hashmap get command by id: command.run
	 */

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message msg = event.getMessage();
		User user = event.getAuthor();

		/*
		if (memify.get(user)) {
			memify(msg);
		}
				
		*/
		/**
		 * Gunga
		 */
		if (msg.getContentRaw().toLowerCase().contains("gunga") && !msg.getAuthor().isBot() && msg.getChannel().getName().equals("gunga-farming")) {
			GUNGACOUNT++;
			event.getChannel().sendMessage("Gunga count: " + GUNGACOUNT).queue();
			FileOperations.writeToFile(FileOperations.gunga, GUNGACOUNT);
		}


		if (!msg.getContentRaw().startsWith(PREFIX))
			return;
		String comCall = StringOperations.removePrefix(msg.getContentRaw());
		String[] callAndFlags = comCall.split(" ", 2);
		String[] comParts = comCall.split(" ");
		String[] flags = (callAndFlags.length > 1) ? StringOperations.parseArgs(callAndFlags[1]) : null;
		System.out.println("FLAGS WE SHOULD SEND" + Arrays.toString(flags));
		for (Command com : commands.values()) {
			if (com.isInCalls(comParts[0])) {
				if (flags != null) {
					com.run(event, flags);
				} else {
					com.run(event);
				}
			}
		}
	}



	public static HashMap<String[], ? extends Command> getCommands() {
		return commands;
	}

}