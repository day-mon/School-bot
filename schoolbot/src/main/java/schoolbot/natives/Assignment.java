package schoolbot.natives;

import schoolbot.natives.util.AssignmentType;

public class Assignment {
    private Professor assignedProfessor;
    private Classroom clazz;
    private String dueDate;
    private String assignmentName;
    private int pointsAmount;
    private AssignmentType assigmentType;


    public Assignment() {

    }

    public Assignment(Classroom clazz, String assignmentName, String dueDate, int pointsAmount, AssignmentType assigmentType) {
        this.assigmentType = assigmentType;
        this.clazz = clazz;
        this.assignmentName = assignmentName;
        this.pointsAmount = pointsAmount;
        this.dueDate = dueDate;
        assignedProfessor = clazz.getProfessor();
        
    }

    public AssignmentType getAssigmentType() {
        return assigmentType;
    }

    public Professor getAssignedProfessor() {
        return assignedProfessor;
    }
    
    public String getAssignmentName() {
        return assignmentName;
    }

    public Classroom getClazz() {
        return clazz;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getPointsAmount() {
        return pointsAmount;
    }

    public void setAssigmentType(AssignmentType assigmentType) {
        this.assigmentType = assigmentType;
    }

    public void setAssignedProfessor(Professor assignedProfessor) {
        this.assignedProfessor = assignedProfessor;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setClazz(Classroom clazz) {
        this.clazz = clazz;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPointsAmount(int pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    @Override
    public String toString() {
        return "Assignment [assigmentType=" + assigmentType + ", assignedProfessor=" + assignedProfessor
                + ", assignmentName=" + assignmentName + ", clazz=" + clazz + ", dueDate=" + dueDate + ", pointsAmount="
                + pointsAmount + "]";
    }
    
    


    
}
