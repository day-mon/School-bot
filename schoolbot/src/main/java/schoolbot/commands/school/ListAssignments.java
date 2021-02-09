package schoolbot.commands.school;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.MessageOperations;
import schoolbot.commands.Command;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;
import schoolbot.natives.School;

public class ListAssignments extends Command {

    public ListAssignments() {
        super(new String[] { "listassignments", "assignments" });
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // For Invalid usage
        Message msg = event.getMessage();
        Command com = this;

        Member userTyping = event.getMember();
        boolean adminCheck = userTyping.hasPermission(Permission.ADMINISTRATOR);
        MessageChannel channel = event.getChannel();

        if (args.length >= 2)  {
            if (adminCheck) {
                String school = args[0];
                if (Ryan.schools.containsKey(school)) {
                    School schoolObj = Ryan.schools.get(school);
                    String classNumber = args[1];
                    if (schoolObj.getListOfClasses().containsKey(classNumber)) {
                        Classroom clazz = Ryan.classes.get(classNumber); 
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
                            assignmentsStringBuilder.append(assignments + "\n");

                            if (assignmentsStringBuilder.length() >= 1750) 
                                MessageOperations.messageExtender(assignmentsStringBuilder, channel);
                        }
                        int pendingAssignments = clazz.getAssignments().size();
                        assignmentsStringBuilder.append("You have " +pendingAssignments + " pending " + (pendingAssignments > 0 ? "assignments!":"assignment!"));
                        assignmentsStringBuilder.append("```");
                        channel.sendMessage(assignmentsStringBuilder).queue();
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com", classNumber + " is not a class number at " + schoolObj.getSchoolName(), msg, com);
                    }
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", school + " does not exist!", msg, com);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "You are not an administrator", event.getMessage(), this);
            }
        } else {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast two arguments!", msg, com);
        }
    }

}
