package schoolbot.natives.util;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
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

    public static void embedAsMessage(String title, Field field,  String footer, Message msg) {
       EmbedBuilder embedBuilder = new EmbedBuilder();
       embedBuilder.setColor(Color.BLACK);
       embedBuilder.setTitle(title);
       embedBuilder.addField(field);
       embedBuilder.setFooter(footer);
       msg.getChannel().sendMessage(embedBuilder.build()).queue();;

    }
}
