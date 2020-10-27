package schoolbot.natives;

import java.util.HashMap;

public class Professor {

    private HashMap<String, Classroom> professorsClasses;
    private HashMap<Student, Classroom> studentsInClasses;
    
    private String email;
    private String prefix;
    private String name;




    Professor() {

    }

    public Professor(String prefix, String name, String email) {
        this.email = email;
        this.name = name;
        this.prefix = prefix;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public void setProfessorsClasses(HashMap<String, Classroom> professorsClasses) {
        this.professorsClasses = professorsClasses;
    }

    public void setStudentsInClasses(HashMap<Student, Classroom> studentsInClasses) {
        this.studentsInClasses = studentsInClasses;
    }

    public String getEmail() {
        return email;
    }

    public String name() {
        return name;
    }

    public HashMap<String, Classroom> getProfessorsClasses() {
        return professorsClasses;
    }

    public HashMap<Student, Classroom> getStudentsInClasses() {
        return studentsInClasses;
    }

    public void addClass(Classroom clazz) {
        professorsClasses.putIfAbsent(clazz.getClassID(), clazz);
    }

    public void addStudent(Student student, Classroom clazz) {
        studentsInClasses.putIfAbsent(student, clazz);
    }

    public boolean removeClass(Classroom clazz) {
        if (professorsClasses.containsKey(clazz.getClassID())) {
            professorsClasses.remove(clazz.getClassID()); 
            return true;
        }
            return false;
    }

    public boolean removeStudent(Student student) {
        if (studentsInClasses.containsKey(student.getRealName())) {
            studentsInClasses.remove(student.getRealName());
            return true;
        } 
        return false;
    }

    
}
