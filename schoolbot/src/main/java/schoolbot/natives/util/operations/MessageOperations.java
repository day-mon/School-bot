package schoolbot.natives.util.operations;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import schoolbot.Ryan;
import schoolbot.natives.Classroom;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.InvalidUsage;

public class MessageOperations {

    public static void messageExtender(StringBuilder s, MessageChannel channel) {
        s.append("```");
        channel.sendMessage(s).queue();
        s.setLength(0);
        s.append("```"); // should there be four ticks here?
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

    public static boolean roleCheck (Classroom c) {
        String [] channelParsed = c.getTextChannel().split("\\-");
        if (c.getRole() == null) {
            for (Role roles : Ryan.jda.getRoles()) {
                String [] roleSplit = roles.getName().contains("-") ? roles.getName().split("-") : roles.getName().split("\s");
                if (roleSplit[roleSplit.length-1].equals(channelParsed[channelParsed.length-1])) {
                    c.setRole(roles);
                    return true;
                }
            }
        }
        return false;
    }
}
