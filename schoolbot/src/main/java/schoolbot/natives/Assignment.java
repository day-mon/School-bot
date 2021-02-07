package schoolbot.natives;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import schoolbot.natives.util.AssignmentType;

public class Assignment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1874828658877582324L;
    private Professor assignedProfessor;
    private Classroom clazz;
    private Date dueDate;
    private LocalDateTime ldt;
    private String assignmentName;
    private double pointsAmount;
    private AssignmentType assigmentType;
    private String assignmentRef;
    private boolean expired = false;
    private int[] intervals;

    public Assignment() {

    }

    public Assignment(Classroom clazz, String assignmentName, Date dueDate, double pointsAmount, String assigmentType) {
        if (assigmentType.startsWith("T") || assigmentType.startsWith("T")) {
            this.assigmentType = AssignmentType.TEST;
        } else if (assigmentType.startsWith("Q") || assigmentType.startsWith("q")) {
            this.assigmentType = AssignmentType.QUIZ;
        } else if (assigmentType.startsWith("E") || assigmentType.startsWith("e")) {
            this.assigmentType = AssignmentType.EXTRA_CREDIT;
        } else if (assigmentType.startsWith("H") || assigmentType.startsWith("h")) {
            this.assigmentType = AssignmentType.HOMEWORK;
        } else {
            this.assigmentType = AssignmentType.HOMEWORK;
        }

        this.clazz = clazz;
        this.assignmentName = assignmentName;
        this.pointsAmount = pointsAmount;
        this.dueDate = dueDate;
        this.assignmentRef = assigmentType;
        assignedProfessor = clazz.getProfessor();
        ldt = LocalDateTime.now();

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

    public String getAssignmentRef() {
        return assignmentRef;
    }

    public Classroom getClazz() {
        return clazz;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getPointsAmount() {
        return pointsAmount;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean e) {
        expired = e;
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


    public void setIntervals(int[] intervals) {
        intervals = new int[intervals.length];
        this.intervals = intervals;
    }

    public void setClazz(Classroom clazz) {
        this.clazz = clazz;
    }

    public void setAssignmentRef(String assignmentRef) {
        this.assignmentRef = assignmentRef;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPointsAmount(double pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    @Override
    public String toString() {
        return "Assignment [assigmentType=" + assigmentType + ", assignedProfessor=" + assignedProfessor
                + ", assignmentName=" + assignmentName + ", assignmentRef=" + assignmentRef + ", clazz=" + clazz
                + ", dueDate=" + dueDate + ", pointsAmount=" + pointsAmount + "]";
    }

}
