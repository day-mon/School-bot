package schoolbot.natives.util;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Provider;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.entities.MessageEmbed.VideoInfo;
import schoolbot.SchoolGirl;
import schoolbot.commands.*;

/**
 * Basic String operations that will be useful for a bot.
 * 
 * @author Elsklivet#8867
 */
public class StringOperations {
    /**
     * Remove all "" marks from a String (for command parsing)
     * 
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeQuotes(String str) throws Exception {
        if (str == null || str.length() <= 2)
            throw new Exception("String is not sized correctly to manipulate: " + str);
        return str.replaceAll("\\\"", "");
    }

    /**
     * Remove bounding "" marks from a String (for command parsing)
     * 
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeBounds(String str) {
        if (str == null || str.length() <= 2)
            return str;
        return str.substring(1, str.length() - 1);
    }

    public static String[] parseArgs(String argu) {
        boolean quote = false;
        String tempstr = "";
        ArrayList<String> args = new ArrayList<String>();
        for (int i = 0; i < argu.length(); i++) { // Some basic looping and splicing to split
            if (argu.charAt(i) == ' ' && !quote && tempstr.trim().length() > 0) {
                args.add(tempstr.trim());
                tempstr = "";
            } else if (i!=0 && argu.charAt(i) == '"' && argu.charAt(i - 1) != '\\') {
                quote = !quote;
            } else {
                if (argu.charAt(i) == '\\' && argu.charAt(i + 1) == '"')
                    continue;
                tempstr += argu.charAt(i);
            }
        }
        if (tempstr.trim().length() > 0) {
            args.add(tempstr.trim());
        }

        String[] returnArray = new String[args.size()];
        for(int i = 0; i<returnArray.length; i++){
            returnArray[i] = args.get(i);
        }

        return returnArray;
    }

    public static String removePrefix(String command) {
        StringBuilder sb = new StringBuilder(command);
        return sb.delete(0, SchoolGirl.PREFIX.length()).toString();
    }

    /**
     * Turn a documentation file into a MessageEmbed.
     * 
     * @param relativePath Path to the documentation file.
     * @return {@code MessageEmbed} version of the documentation file.
     */
    public static MessageEmbed parseDoc(String relativePath) {
        File file;
        FileReader fileRead;
        BufferedReader reader;
        ArrayList<String> lines = new ArrayList<String>(); // or queue
        try {
            file = new File(relativePath);
            fileRead = new FileReader(file);
            reader = new BufferedReader(fileRead);
        } catch (FileNotFoundException fnfx) {
            return null;
        }
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("#")) lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = "https://github.com/tykoooo/School-bot/tree/master/schoolbot";
        String title = lines.get(0);
        String description = lines.get(3);
        EmbedType type = EmbedType.RICH;
        OffsetDateTime timestamp = OffsetDateTime.now();
        int color = 0;
        Thumbnail thumbnail = null;
        Provider siteProvider = null;
        AuthorInfo author = new AuthorInfo("SchoolBot", "https://github.com/tykoooo/School-bot/tree/master/schoolbot", null, null);
        VideoInfo videoInfo = null;
        Footer footer = new Footer("Report errors to damon#9999 or Elsklivet#8867", null, null);
        ImageInfo image = null;
        List<Field> fields = new ArrayList<MessageEmbed.Field>();
        fields.add(new Field("Aliases",lines.get(1),true));
        fields.add(new Field("Flags",lines.get(2),true));

        MessageEmbed embed = new MessageEmbed(url,
        title,
        description,
        type,
        timestamp,
        color,
        thumbnail,
        siteProvider,
        author,
        videoInfo,
        footer,
        image,
        fields);
        
        return embed;
    }

    /*
        Add some "correctXYZ" methods here
    */ 
}
