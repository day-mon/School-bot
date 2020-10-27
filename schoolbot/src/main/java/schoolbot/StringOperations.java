package schoolbot;

/** Basic String operations that will be useful for a bot.
 * @author Elsklivet#8867
 */
public class StringOperations{
    /** Remove all "" marks from a String (for command parsing)
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeQuotes(String str){
        if(str == null || str.length() <= 2) throw new Exception("String is not sized correctly to manipulate: "+str);
        return str.replaceAll("\\\"", "");
    }

    /** Remove bounding  "" marks from a String (for command parsing)
     * @param str String to remove all quotations from
     * @return String without any quotation marks
     */
    public static String removeBounds(String str){
        if(str == null || str.length() <= 2) throw new Exception("String is not sized correctly to manipulate: "+str);
        return str.substring(1,str.length()-1);
    }
}
