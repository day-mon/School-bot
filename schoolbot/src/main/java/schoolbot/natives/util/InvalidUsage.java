package schoolbot.natives.util;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Provider;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.entities.MessageEmbed.VideoInfo;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;

public class InvalidUsage {

    protected MessageEmbed InvalidUsage;

    public InvalidUsage(String urlToCommand, String commandName, String problemWithUsage, Message msg) {
        String authorOfMessage = msg.getAuthor().getName();
        EmbedType type = EmbedType.RICH;
        OffsetDateTime time = OffsetDateTime.now();
        int color = 1;
        Thumbnail thumbnail = null;
        Provider siteProvider = null;
        AuthorInfo author = new AuthorInfo("SchoolBot", "https://github.com/tykoooo/School-bot/tree/master/schoolbot", null, null);
        VideoInfo videoInfo = null;
        Footer footer = new Footer("Report errors to damon#9999 or Elsklivet#8867", null, null);
        ImageInfo image = null;
        List<Field> fields = new ArrayList<MessageEmbed.Field>();

        MessageEmbed embed = new MessageEmbed(urlToCommand,
        commandName,
        problemWithUsage,
        type,
        time,
        color,
        thumbnail,
        siteProvider,
        author,
        videoInfo,
        footer,
        image,
        fields);
        } 
        

        public MessageEmbed getInvalidUsage() {
            return InvalidUsage;
        }
}
