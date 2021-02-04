package schoolbot.natives;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import schoolbot.natives.util.AssignmentType;

public class Assignment extends TimerTask implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1874828658877582324L;
    private Professor assignedProfessor;
    private Classroom clazz;
    private Date dueDate;
    private String assignmentName;
    private double pointsAmount;
    private AssignmentType assigmentType;
    private String assignmentRef;
    private Timer timer;
    TimerTask task;

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
        this.assignmentRef = clazz.getClassID() + assigmentType + "_" + clazz.getAssignments().size();
        assignedProfessor = clazz.getProfessor();
        timer.schedule(task, dueDate);

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

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        if (dueDate.compareTo(time))
    }

}
