package schoolbot.natives;

import java.util.HashMap;

public class Professor {

    private HashMap<String, Classroom> professorsClasses;
    private HashMap<Student, Classroom> studentsInClasses;
    
    private String email;
    private String prefix;
    private String name;
    private int age;
    private String officeHours;
    private School professorsSchool;




    public Professor() {

    }

    public Professor(String name, String email, School professorsSchool) {
        this.email = email;
        this.name = name;
        this.professorsSchool = professorsSchool;
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

    

    /**
     * @return String return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return String return the officeHours
     */
    public String getOfficeHours() {
        return officeHours;
    }

    /**
     * @param officeHours the officeHours to set
     */
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    /**
     * @return School return the professorsSchool
     */
    public School getProfessorsSchool() {
        return professorsSchool;
    }

    /**
     * @param professorsSchool the professorsSchool to set
     */
    public void setProfessorsSchool(School professorsSchool) {
        this.professorsSchool = professorsSchool;
    }

}
