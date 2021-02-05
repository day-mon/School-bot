package schoolbot.natives.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import schoolbot.commands.Command;

public class InvalidUsage {

    protected MessageEmbed InvalidUsage;

    /**
     *
     * @param urlToCommand
     * @param problemWithUsage
     * @param msg
     * @param com
     */
    public InvalidUsage(String urlToCommand, String problemWithUsage, Message msg, Command com) {

        ArrayList<String> lines = FileOperations.parseDoc(com);
        String authorOfMessage = msg.getAuthor().getName();
        EmbedType type = EmbedType.RICH;
        OffsetDateTime time = OffsetDateTime.now();
        int color = 2;
        Thumbnail thumbnail = null;
        Provider siteProvider = null;
        AuthorInfo author = new AuthorInfo("SchoolBot", "https://github.com/tykoooo/School-bot/tree/master/schoolbot",
                null, null);
        VideoInfo videoInfo = null;
        Footer footer = new Footer("Incorrect usage by: " + authorOfMessage, null, null);
        ImageInfo image = null;
        List<Field> fields = new ArrayList<MessageEmbed.Field>();
        fields.add(new Field("Aliases", Arrays.toString(com.getCalls()), true));
        fields.add(new Field("Flags", lines.get(2), true));

        MessageEmbed embed = new MessageEmbed(urlToCommand, com.getName(), problemWithUsage, type, time, color,
                thumbnail, siteProvider, author, videoInfo, footer, image, fields);

        InvalidUsage = embed;
    }

    public MessageEmbed getInvalidUsage() {
        return InvalidUsage;
    }

}
