package schoolbot.natives.util;

import java.util.ArrayList;
import schoolbot.commands.*;

/** Basic String operations that will be useful for a bot.
 * @author Elsklivet#8867
 */
public class StringOperations{
    /** Remove all "" marks from a String (for command parsing)
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeQuotes(String str) throws Exception{
        if(str == null || str.length() <= 2) throw new Exception("String is not sized correctly to manipulate: "+str);
        return str.replaceAll("\\\"", "");
    }

    /** Remove bounding  "" marks from a String (for command parsing)
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeBounds(String str) throws Exception{
        if(str == null || str.length() <= 2) throw new Exception("String is not sized correctly to manipulate: "+str);
        return str.substring(1,str.length()-1);
    }

    public static String[] parseArgs(String argu){
        boolean quote = false;
        String tempstr = "";
        ArrayList<String> args = new ArrayList<String>();
        for (int i = 0; i < argu.length(); i++) { // Some basic looping and splicing to split
	    	if (argu.charAt(i) == ' ' && !quote && tempstr.trim().length() > 0) {
	    		args.add(tempstr.trim());
	    		tempstr = "";
	    	} else if (argu.charAt(i) == '"' && argu.charAt(i - 1) != '\\') {
	    		quote = !quote;
	    	} else {
	    		if (argu.charAt(i) == '\\' && argu.charAt(i + 1) == '"') continue;
	    		tempstr += argu.charAt(i);
	    	}
	    }
	    if (tempstr.trim().length() > 0) {
	    	args.add(tempstr.trim());
	    }

        return (String[])args.toArray();
    }

    /*
        Add some "correctXYZ" methods here
    */ 
}
