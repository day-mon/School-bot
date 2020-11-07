package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.commands.Command;
import schoolbot.natives.util.Majors;

public class ListMajors extends Command {

    public ListMajors() {
        super(new String[]{"listmajors", "majors"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
            MessageChannel channel = event.getChannel();
            StringBuilder kindaAnArray = new StringBuilder("[");

            for (Majors majors : Majors.values()) {
                String major = majors.toString();

                if (major.contains("_")) {
                    major=Character.toString(major.charAt(0)).toUpperCase() + 
            
                    major.substring(1, major.indexOf("_")).toLowerCase() +
                    major.substring(major.indexOf("_")+1, major.length()).toLowerCase();
                } else {
                    major=Character.toString(major.charAt(0)).toUpperCase()
                    + major.substring(1, major.length()-1).toLowerCase();
            
                }
                kindaAnArray.append(major + ", ");
            }
            kindaAnArray.append("]");
            channel.sendMessage(kindaAnArray + "\n").queue();

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // TODO Auto-generated method stub

    }
    
}