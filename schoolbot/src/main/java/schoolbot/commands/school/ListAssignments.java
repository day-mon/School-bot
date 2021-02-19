package schoolbot.commands.school;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.operations.MessageOperations;
import schoolbot.natives.util.operations.StringOperations;
import schoolbot.natives.util.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;

public class ListAssignments extends Command {

    public ListAssignments() {
        super(new String[] { "listassignments", "assignments" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        String textChannel = channel.getName();


        for (Classroom clazz : Ryan.classes.values()) {
            if (clazz.getTextChannel().equals(textChannel)) {
                if (clazz.getAssignments().size() <= 0) {
                    MessageOperations.invalidUsageShortner("https://google.com", "There are no assignments for: " + clazz.getClassName(), event.getMessage(), this);
                    return;
                }
                StringBuilder assignmentsStringBuilder = new StringBuilder("```Assignments for " + clazz.getClassName() + " (" + clazz.getClassNum() + ")" + ": \n") ;
                for (Assignment assignments : clazz.getAssignments().values().stream().sorted().collect(Collectors.toList())) {
                    if (!assignments.isExpired()) {
                        assignmentsStringBuilder.append(assignments + "\n");
                    }

                    if (assignmentsStringBuilder.length() >= 1750) 
                        MessageOperations.messageExtender(assignmentsStringBuilder, channel);
                }

                int pendingAssignments = 0;
                for (Assignment assignment : clazz.getAssignments().values()) {
                    if (!assignment.isExpired()) {
                        pendingAssignments += 1;
                    }
                }
                
                assignmentsStringBuilder.append("You have " + pendingAssignments + " pending " + (pendingAssignments >= 0 ? "assignments!":"assignment!") + "\n");
                
                
                Assignment[] assignmentsArray = new Assignment[pendingAssignments];
                assignmentsArray =  clazz.getAssignments().values().toArray(assignmentsArray);
                Arrays.sort(assignmentsArray);
                Assignment closestAssignmentDue = assignmentsArray[0];
                int postion = 0;
                while (assignmentsArray[postion].isExpired() && postion < assignmentsArray.length-1) {
                    postion++;
                }

                closestAssignmentDue = assignmentsArray[postion];

                long dueDate =  (closestAssignmentDue.getDueDate().getTime() / 1000) - (System.currentTimeMillis() / 1000);



                assignmentsStringBuilder.append("Your closest assignment is: " + (closestAssignmentDue.isExpired() ? "Nothing" :   closestAssignmentDue.getAssignmentName()) + "\nIt is due in: " + (closestAssignmentDue.isExpired() ?  "N/A" : StringOperations.formatTime(dueDate)));

                
                
                assignmentsStringBuilder.append("```");
                channel.sendMessage(assignmentsStringBuilder).queue();
                return;
            }
        }

        MessageOperations.invalidUsageShortner("https://google.com", "This text channel is not associated with any class!", event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // For Invalid usage
        Message msg = event.getMessage();
        Command com = this;


        Member userTyping = event.getMember();
        boolean adminCheck = userTyping.hasPermission(Permission.ADMINISTRATOR);
        MessageChannel channel = event.getChannel();

        if (args.length >= 1)  {
            if (adminCheck) {
                String classNumber = args[0];
                if (Ryan.classes.containsKey(classNumber)) {
                    School schoolObj = Ryan.classes.get(classNumber).getSchool();
                    if (schoolObj.getListOfClasses().containsKey(classNumber)) {
                        Classroom clazz = schoolObj.getListOfClasses().get(classNumber); 
                        /**
                         * for some reason schoolObj.getclasses.getassignments would be 0 ??
                         * 
                         */
                      
                      if (clazz.getAssignments().size() <= 0) {
                            MessageOperations.invalidUsageShortner("https://google.com", "There are no assignments for: " + clazz.getClassName(), msg, com);
                            return;
                        }
                        StringBuilder assignmentsStringBuilder = new StringBuilder("```Assignments for " + clazz.getClassName() + " (" + clazz.getClassNum() + ")" + ": \n") ;
                        for (Assignment assignments : clazz.getAssignments().values()) {
                            if (!assignments.isExpired()) {
                                assignmentsStringBuilder.append(assignments + "\n");
                            }

                            if (assignmentsStringBuilder.length() >= 1750) 
                                MessageOperations.messageExtender(assignmentsStringBuilder, channel);
                        }
                        int pendingAssignments = 0;
                        for (Assignment assignment : clazz.getAssignments().values()) {
                            if (!assignment.isExpired()) {
                                pendingAssignments += 1;
                            }
                        }
                        assignmentsStringBuilder.append("You have " + pendingAssignments + " pending " + (pendingAssignments >= 0 ? "assignments!":"assignment!") + "\n");
                        
                        Assignment[] assignmentsArray = new Assignment[pendingAssignments];
                        assignmentsArray = clazz.getAssignments().values().toArray(assignmentsArray);
                        Arrays.sort(assignmentsArray);
                        int postion = 0;
                        Assignment closestAssignmentDue = assignmentsArray[0];

                        while (assignmentsArray[postion].isExpired() && postion < assignmentsArray.length-1) {
                            postion++;
                        }

                        closestAssignmentDue = assignmentsArray[postion];

                        long dueDate =  (closestAssignmentDue.getDueDate().getTime() / 1000) - (System.currentTimeMillis() / 1000);


                        assignmentsStringBuilder.append("Your closest assignment is: " + (closestAssignmentDue.isExpired() ? "Nothing" :   closestAssignmentDue.getAssignmentName()) + "\nIt is due in: " + (closestAssignmentDue.isExpired() ?  "N/A" : 
                        StringOperations.formatTime(dueDate)));
                        
                        assignmentsStringBuilder.append("```");
                        channel.sendMessage(assignmentsStringBuilder).queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", classNumber + " is not a class number at " + schoolObj.getSchoolName(), msg, com);
                    }
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", "That class does not exist!", msg, com);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "You are not an administrator", event.getMessage(), this);
            }
        } else {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast two arguments!", msg, com);
        }
    }

}

