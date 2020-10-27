package schoolbot.natives;

import java.util.Date;
import java.util.HashMap;

public class Classroom {

    private String classID;
    private Date time;
    private String year;
    private int credits;
    private Professor professor;
    private HashMap<String, Student> classList;

    public Classroom() {

    }

    public Classroom(String classID, Date time, String year, int credits, Professor professor) {
        this.classID = classID;
        this.time = time;
        if (!year.equalsIgnoreCase("freshman") || (!year.equalsIgnoreCase("sophmore") || !year.equalsIgnoreCase("junior") || (!year.equalsIgnoreCase("senior")))) {
            if (year.startsWith("f")) year = "Freshman";
            else if (year.startsWith("so")) year = "Sophmore";
            else if (year.startsWith("j")) year = "Junior";
            else if (year.startsWith("se")) year = "Senior";
            else year = "Freshman";
        }
        this.year = year;
        this.credits = credits;
        professor.addClass(this);
    }

    public String getClassID() {
        return this.classID;
    }

    public HashMap<String, Student> getClassList() {
        return classList;
    }

    public int getCredits() {
        return credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getYear() {
        return year;
    }

    public Date getTime() {
        return this.time;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setClassList(HashMap<String, Student> classList) {
        this.classList = classList;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void addStudent(Student student) {
        classList.putIfAbsent(student.getRealName(), student);
    }

    public boolean removeStudent(Student student) {
        if (classList.containsKey(student.getRealName())) {
            classList.remove(student.getRealName());
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classID == null) ? 0 : classID.hashCode());
        result = prime * result + ((classList == null) ? 0 : classList.hashCode());
        result = prime * result + credits;
        result = prime * result + ((professor == null) ? 0 : professor.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Classroom other = (Classroom) obj;
        if (classID == null) {
            if (other.classID != null)
                return false;
        } else if (!classID.equals(other.classID))
            return false;
        if (classList == null) {
            if (other.classList != null)
                return false;
        } else if (!classList.equals(other.classList))
            return false;
        if (credits != other.credits)
            return false;
        if (professor == null) {
            if (other.professor != null)
                return false;
        } else if (!professor.equals(other.professor))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Classroom [classID= " + classID + ", classList= " + classList + ", credits= " + credits + ", professor= "
                + professor + ", time=" + time + ", year=" + year + "]";
    }
}


