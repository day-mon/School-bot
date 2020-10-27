package schoolbot.natives;

import java.util.Date;

public class Classroom {

    private String classID;
    private Date time;
    private String year;
    private int credits;
    private Professor professor;

    public Classroom() {

    }

    public Classroom(String classID, Date time, String year, int credits) {
        this.classID = classID;
        this.time = time;
        if (!year.equalsIgnoreCase("freshman") || (!year.equalsIgnoreCase("sophomore") || !year.equalsIgnoreCase("junior") || (!year.equalsIgnoreCase("senior")))) {
            if (year.startsWith("f")) year = "Freshman";
            else if (year.startsWith("so")) year = "Sophomore";
            else if (year.startsWith("j")) year = "Junior";
            else if (year.startsWith("se")) year = "Senior";
        }
        this.year = year;
        this.credits = credits;
    }

    public String getClassID() {
        return this.classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}


