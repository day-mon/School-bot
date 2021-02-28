package schoolbot.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Event;
import schoolbot.natives.util.operations.FileOperations;

public class Gunga extends Event {

    public Gunga() {
      super(new String[] {"gunga"});
    }

  

	@Override
    public void run(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        String discordMention = msg.getAuthor().getAsTag();


        if (!msg.getAuthor().isBot() && channel.getName().equals("gunga-farming")) {
            String [] message = msg.getContentRaw().split("\\s"); {
            for (String messages : message) {
                if (messages.toLowerCase().equals("gunga")) {
                    if (Ryan.gunga.containsKey(discordMention)) {
                        Ryan.gunga.put(discordMention, Ryan.gunga.get(discordMention)+1);
                        Ryan.GUNGA_COUNT++;
                    } else {
                        Ryan.gunga.put(discordMention, 1);
                        Ryan.GUNGA_COUNT++;
                    }

                    }
                }
            }

            System.out.println("Discord: " + discordMention + " Gunga count: " + Ryan.gunga.get(discordMention));
            


			event.getChannel().sendMessage("Gunga count: " + Ryan.GUNGA_COUNT).queue();
			FileOperations.writeToFile(FileOperations.gunga, Ryan.GUNGA_COUNT);
            FileOperations.writeToFile(FileOperations.gungaLeadeboard, Ryan.gunga);
		}


    }
}
    
    

