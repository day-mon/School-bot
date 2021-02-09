package schoolbot.natives.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Provider;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.entities.MessageEmbed.VideoInfo;
import schoolbot.Ryan;

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
        if (str == null || str.length() <= 2 || str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"')
            return str;

        return str.substring(1, str.length() - 1);
    }

    /**
     * Splits a single string of CLI arguments into their respective parts as a list
     * based on ' ' spaces and quotation marks.
     * 
     * @param argu Arguments list to send
     * @return Split arguments without quotation marks
     */
    public static String[] parseArgs(String argu) {
        boolean quote = false;
        String tempstr = "";
        ArrayList<String> args = new ArrayList<String>();
        for (int i = 0; i < argu.length(); i++) { // Some basic looping and splicing to split
            if (argu.charAt(i) == ' ' && !quote && tempstr.trim().length() > 0) {
                args.add(tempstr.trim());
                tempstr = "";
            } else if (argu.charAt(i) == '"') {
                if (i == 0)
                    quote = !quote;
                else if (argu.charAt(i - 1) != '\\')
                    quote = !quote;
            } else {
                if (argu.charAt(i) == '\\' && argu.charAt(i + 1) == '"')
                    continue;
                tempstr += argu.charAt(i);
            }
        }
        if (tempstr.trim().length() > 0) {
            System.out.println(tempstr.trim());
            args.add(tempstr.trim());
        }

        String[] returnArray = new String[args.size()];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = args.get(i);
        }

        return returnArray;
    }

    public static String removePrefix(String command) {
        StringBuilder sb = new StringBuilder(command);
        return sb.delete(0, Ryan.PREFIX.length()).toString();
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
                if (!line.startsWith("#"))
                    lines.add(line);
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
        AuthorInfo author = new AuthorInfo("SchoolBot", "https://github.com/tykoooo/School-bot/tree/master/schoolbot",
                null, null);
        VideoInfo videoInfo = null;
        Footer footer = new Footer("Report errors to damon#9999 or Elsklivet#8867", null, null);
        ImageInfo image = null;
        List<Field> fields = new ArrayList<MessageEmbed.Field>();
        fields.add(new Field("Aliases", lines.get(1), true));
        fields.add(new Field("Flags", lines.get(2), true));
        fields.add(new Field("Example", lines.get(4), true));

        MessageEmbed embed = new MessageEmbed(url, title, description, type, timestamp, color, thumbnail, siteProvider,
                author, videoInfo, footer, image, fields);

        return embed;
    }

    public static String normalizeCapitals(String str) {
        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (i == 0 && curr != ' ') {
                newStr.append(str.substring(i, i + 1).toUpperCase());
            } else if (curr != ' ' && str.charAt(i - 1) != ' ') {
                newStr.append(str.substring(i, i + 1).toLowerCase());
            } else if (str.charAt(i - 1) == ' ') {
                newStr.append(str.substring(i, i + 1).toUpperCase());
            } else {
                newStr.append(str.substring(i, i + 1));
            }
        }

        return newStr.toString();
    }

    public static String formatTime(long seconds) { // sorry again - you know who
        String out = "";
        int mins = (int) seconds / 60;
        seconds -= (mins * 60);
        int hours = mins / 60;
        mins -= (hours * 60);
        int days = hours / 24;
        hours -= (days * 24);
        int weeks = days / 7;
        days -= (weeks * 7);
        out += (weeks > 0 ? weeks + " weeks, " : "") + (days > 0 ? days + " days, " : "")
                + (hours > 0 ? hours + " hours, " : "") + (mins > 0 ? mins + " minutes and " : "") + seconds
                + " seconds.";
        return out;
    }

    public static Date formatClassTime(String input) throws ParseException { // sorry :( - Ryan
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm");
        int at = input.indexOf("@");
        String[] splitAt = input.split("@");
        if (input.charAt(at - 1) != ' ') {
            input = splitAt[0] + " @" + splitAt[1];
        }
        if (input.charAt(at + 1) != ' ') {
            input = splitAt[0] + "@ " + splitAt[1];
        }
        String time = input.split(" ")[2].toLowerCase(); // issues
        boolean am = time.contains("am");
        int mins = Integer.parseInt(time.contains(":") ? time.split(":")[1].replace("am", "").replace("pm", "") : "0");
        int hour = Integer.parseInt(time.contains(":") ? time.split(":")[0] : time.replace("am", "").replace("pm", ""))
                + (am ? 0 : 12);
        hour = (hour == 12 && am ? 0 : hour);
        String d = Ryan.today.getMonth().getValue() + "/" + Ryan.today.getDayOfMonth() + "/" + Ryan.today.getYear()
                + " " + hour + ":" + (mins == 0 ? "00" : mins);
        return sdf.parse(d);
    }

    public static boolean numericCheck(String numeric) {
        return numeric.matches("-?\\d+(\\.\\d+)?");
    }

    public static String formatMatrix(double[][] a) {
        String s = "```";
        for (int i = 0; i < a.length; i++) {
            s += "[";
            for (int j = 0; j < a[0].length; j++) {
                s += a[i][j] + " ";
            }
            s = s.substring(0, s.length() - 1) + "]" + (i != a.length - 1 ? "\n" : "");
        }
        s += "```";
        return s;
    }

    /*
     * Add some "correctXYZ" methods here
     */
}
