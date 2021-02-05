package schoolbot.natives.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import schoolbot.commands.Command;

public class MessageOperations {

    public static void messageExtender(StringBuilder s, MessageChannel channel) {
        s.append("```");
        channel.sendMessage(s).queue();
        s.setLength(0);
        s.append("````"); // should there be four ticks here?
    }

    /**
     * Added just to make code look a little more clean.
     * 
     * @param link
     * @param usageIssue
     * @param msg
     * @param com
     * @param channel
     * @return void
     */
    public static void invalidUsageShortner(String link, String usageIssue, Message msg, Command com) {
        msg.getChannel().sendTyping();
        msg.getChannel().sendMessage(new InvalidUsage(link, usageIssue, msg, com).getInvalidUsage()).queue();
    }
}
