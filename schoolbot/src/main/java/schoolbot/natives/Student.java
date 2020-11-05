package schoolbot.natives;

import java.util.HashMap;
import java.util.LinkedList;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.entities.GuildImpl;

/** A student: Joshigakusei's way of handling users.
 * @author Elsklivet#8867
 */
public class Student{
    /**
     * School this student is attending.
     * 
     */
    private School mySchool;
    /**
     * List of classes this student is in.
     * 
     */
    private HashMap<String, Classroom> myClasses; 
    /**
     * Student's GPA.
     * 
     */
    private double GPA;
    /**
     * List of student's majors/minors
     * 
     */
    private LinkedList<String> majors;
    /**
     * Student's real name
     * 
     */
    private String realName;
    /**
     * Amount of time a user wants to be mentioned for an Event occuringx
     */
    private int frequencies;

    /**
     * 
     * @param guild Guild the student is a member of.
     * @param user  User account.
     */
    public Student(GuildImpl guild, User user) {
        this.myClasses = null;
        this.mySchool = null;
        this.GPA = -1.0;
        this.majors = null;
        this.realName = "John Doe";
    }

    public Student(GuildImpl guild, School mySch, double GPA, String[] major, String realName) {
        this.myClasses = new HashMap<String, Classroom>();
        this.mySchool = mySch;
        this.GPA = GPA;
        this.majors = new LinkedList<String>();
        for(String maj : major) this.majors.add(maj);
        this.realName = realName;
    }




    /** Remove a class from this student's schedule.
     * @param clazz Class ({@code Classroom}) to remove
     * @return 
     */
    public void addClass(Classroom clazz){
        this.myClasses.putIfAbsent(clazz.getClassID(), clazz);
    }

    /** 
     * @param major Major to add to this student's list
     */
    public void addMajor(String major){
        this.majors.add(major);
    }

    /** Remove a class from this student's schedule.
     * @param clazz Class ({@code Classroom}) to remove
     * @return 
     */
    public boolean removeClass(Classroom clazz){
        if(myClasses.containsKey(clazz.getClassID())){
            myClasses.remove(clazz.getClassID());
            return true;
        }
        return false;
    }

    // GETTER SETTERS -------------------------------------------------------
    public School getSchool() {
        return mySchool;
    }

    public int getFrequencies() {
        return frequencies;
    }

    public void setSchool(School mySchool) {
        this.mySchool = mySchool;
    }

    public HashMap<String, Classroom> getClasses() {
        return myClasses;
    }

    public void setClasses(HashMap<String, Classroom> myClasses) {
        this.myClasses = myClasses;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double gPA) {
        GPA = gPA;
    }

    public LinkedList<String> getMajors() {
        return majors;
    }

    public void setMajors(LinkedList<String> majors) {
        this.majors = majors;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setFrequencies(int frequencies) {
        this.frequencies = frequencies;
    }
    // ----------------------------------------------------------------------

    /** Get this student's data as a pretty MessageEmbed.
     * @return {@code MessageEmbed} to send in discord messages.
     */
    public MessageEmbed getAsEmbed(){
        return null; // this fat cock nibbler will be from {JDA/src/main/java/net/dv8tion/jda/api/entities/MessageEmbed.java}
    }

    // DEFAULT OVERRIDES
    @Override
    public String toString() {
        return "Student [GPA=" + GPA + ", majors=" + majors + ", myClasses=" + myClasses + ", mySchool=" + mySchool
                + ", realName=" + realName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(GPA);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((majors == null) ? 0 : majors.hashCode());
        result = prime * result + ((myClasses == null) ? 0 : myClasses.hashCode());
        result = prime * result + ((mySchool == null) ? 0 : mySchool.hashCode());
        result = prime * result + ((realName == null) ? 0 : realName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (Double.doubleToLongBits(GPA) != Double.doubleToLongBits(other.GPA))
            return false;
        if (majors == null) {
            if (other.majors != null)
                return false;
        } else if (!majors.equals(other.majors))
            return false;
        if (myClasses == null) {
            if (other.myClasses != null)
                return false;
        } else if (!myClasses.equals(other.myClasses))
            return false;
        if (mySchool == null) {
            if (other.mySchool != null)
                return false;
        } else if (!mySchool.equals(other.mySchool))
            return false;
        if (realName == null) {
            if (other.realName != null)
                return false;
        } else if (!realName.equals(other.realName))
            return false;
        return true;
    }

}
