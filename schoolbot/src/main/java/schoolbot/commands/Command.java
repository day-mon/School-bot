package schoolbot.commands;

import java.util.LinkedList;
import java.util.function.Consumer;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

/** Command interface for all commands to implement. 
 * @author Elsklivet#8867
 */
public abstract class Command {

    /** List of possible aliases for this command. Joshigakusei should check if the command called is in this calls list.
     */
    protected String[] calls;
    /** Enabled status. Commands which are not enabled should not run.
     */
    protected boolean enabled;

    /** Empty constructor.
     * @apiNote This will set the command to {@code disabled}.
     */
    public Command(){
        this.enabled = false;
    }

    /** Command constructor with aliases for the command.
     * @param aliases command aliases
     */
    public Command(String[] aliases){
        calls = new String[aliases.length];
        for(int i = 0; i<aliases.length; i++)
            calls[i] = aliases[i];
        this.enabled = true;
    }

    /** Speed testing constructor. Do not use.
     * @deprecated DO NOT USE THIS CONSTRUCTOR. For speed testing purposes.
     * @param aliases aliases for this command.
     * @param runCode code to run immediately on creation.
     * @param flags flags for immediate run.
     */
    public Command(String[] aliases,  String[] flags, Consumer<String[]> runCode){
        calls = new String[aliases.length];
        for(int i = 0; i<aliases.length; i++)
            calls[i] = aliases[i];
        this.enabled = true;
        runCode.accept(flags);
    }

    /** What the command will do on call.
     */
    public abstract void run(MessageReceivedEvent event);
    
    /** What the command will do on call.
     * @param args Arguments sent to the command.
     */
    public abstract void run(MessageReceivedEvent event, String[] args);


    /** Check whether the current command is enabled or not.
     * @return enabled?
     */
    public boolean enabled(){ return this.enabled; }

    /** Set this command to be enabled or disabled.
     * @param enabled {@code true} for enabled or {@code false} for disabled.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
