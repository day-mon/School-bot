package schoolbot;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.entities.GuildImpl;

/** A student: Mesukousei's way of handling users.
 * @author Elsklivet#8867
 */
public class Student extends net.dv8tion.jda.internal.entities.MemberImpl {
    /**
     * School this student is attending.
     * 
     */
    private School mySchool;
    /**
     * List of classes this student is in.
     * 
     */
    private ArrayList<Classroom> myClasses; // We're gonna make this a hashmap<string, classroom>
    /**
     * Student's GPA.
     * 
     */
    private double GPA;
    /**
     * List of student's majors/minors
     * 
     */
    private ArrayList<String> majors;
    /**
     * Student's real name
     * 
     */
    private String realName;

    /**
     * 
     * @param guild Guild the student is a member of.
     * @param user  User account.
     */
    public Student(GuildImpl guild, User user) {
        super(guild, user);
    }

    public Student(GuildImpl guild, User user, School mySch, double GPA, String major, String realName) {
        super(guild, user);

    }

    public void addClass(Classroom clazz){
        this.myClasses.add(clazz);
    }

    /** 
     * @param major Major to add to this student's list
     */
    public void addMajor(String major){
        this.majors.add(major);
    }

    /** Remove a class from this student's schedule.
     * @deprecated This is not finished: will use hashmap later.
     * @param clazz Class ({@code Classroom}) to remove
     * @return 
     */
    public boolean removeClass(Classroom clazz){
        for(Classroom clazzy : myClasses){
            if(clazz.equals(clazzy)){
                myClasses.remove(clazzy);
                return true;
            }
        }
        return false;
    }

    // GETTER SETTERS -------------------------------------------------------
    public School getSchool() {
        return mySchool;
    }

    public void setSchool(School mySchool) {
        this.mySchool = mySchool;
    }

    public ArrayList<Classroom> getClasses() {
        return myClasses;
    }

    public void setClasses(ArrayList<Classroom> myClasses) {
        this.myClasses = myClasses;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double gPA) {
        GPA = gPA;
    }

    public ArrayList<String> getMajors() {
        return majors;
    }

    public void setMajors(ArrayList<String> majors) {
        this.majors = majors;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
