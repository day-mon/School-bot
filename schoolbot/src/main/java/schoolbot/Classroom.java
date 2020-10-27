package schoolbot;

import java.util.Date;

public class Classroom {

    private String classID;
    private Date time;

    public Classroom() {
        
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
