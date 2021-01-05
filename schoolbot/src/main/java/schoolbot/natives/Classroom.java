package schoolbot.natives;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Classroom {

    private String classID;
    private String classNum;
    private String className;
    private String time;
    private String year;
    private String subject;
    private int credits;
    private Professor professor;
    private School school;
    private HashMap<String, Student> classList;

    public Classroom() {

    }

    public Classroom(String className, String classID, String classNum, String time, String year, int credits, Professor professor, School school) {
        this.classID = classID;
        this.time = time;
        this.school = school;
        this.className = className;
        this.classNum = classNum;
        if (!year.equalsIgnoreCase("freshman") || (!year.equalsIgnoreCase("sophomore") || !year.equalsIgnoreCase("junior") || (!year.equalsIgnoreCase("senior")))) {
            if (year.startsWith("f")) year = "Freshman";
            else if (year.startsWith("so")) year = "Sophomore";
            else if (year.startsWith("j")) year = "Junior";
            else if (year.startsWith("se")) year = "Senior";
            else year = "Unknown";
        }
        this.year = year;
        this.credits = credits;
        professor.addClass(this);
    }

    public String getClassID() {
        return this.classID;
    }

    public String getClassName() {
        return className;
    }

    public String getSubject() {
        return subject;
    }

    public School getSchool() {
		return this.school;
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

    public String getTime() {
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

    public void setSchool(School school) {
        this.school = school;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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



    /**
     * @return String return the classNum
     */
    public String getClassNum() {
        return classNum;
    }

    /**
     * @param classNum the classNum to set
     */
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classID == null) ? 0 : classID.hashCode());
        result = prime * result + ((classList == null) ? 0 : classList.hashCode());
        result = prime * result + ((classNum == null) ? 0 : classNum.hashCode());
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
        if (classNum == null) {
            if (other.classNum != null)
                return false;
        } else if (!classNum.equals(other.classNum))
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
        return   "ClassID: " + classID +  "\n" +
                 "ClassList: " + classList + "\n" +
                 "ClassNum: " + classNum +  "\n" +
                 "Credits: " + credits +  "\n" + 
                 "Professor: " + professor.getLastName() + ", " + professor.getFirstName() + "\n" +
                 "Time: " + time + "\n" +
                 "Year: " + year; 
    }



}


