package schoolbot.natives.util;

import java.awt.Color;
import java.time.OffsetDateTime;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;

public class InvalidUsage {

    protected MessageEmbed InvalidUsage;

    public InvalidUsage(@Nullable String urlToCommand, String commandName, String problemWithUsage, Message msg) {
        String author = msg.getAuthor().getName();
    } 
    
}
