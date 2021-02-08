package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.School;
import schoolbot.natives.Student;
import schoolbot.natives.util.MessageOperations;

public class EditSelf extends Command {

    public EditSelf() {
        super(new String[] { "editself", "selfedit" });

    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        User usertyping = event.getAuthor();
        MessageChannel channel = event.getChannel();
        /**
         * This could be done wrong I am kinda confused I will get conformation later
         * args[0] = thing you want to edit (i.e name, school, gpa) args[1]
         * 
         * 
         * return "Name: " + realName + "\n" + "School" + mySchool.getEmailSuffix() +
         * "\n" + "Major: " + majors.getFirst() + "\n" + "GPA: " + GPA + "\n" + "Amount
         * of classes" + myClasses.size() + "\n" +
         * "-------------------------------------------------"; }
         */

        if (Ryan.students.containsKey(usertyping)) {
            Student studentToEdit = Ryan.students.get(usertyping);
            switch (args[0]) {

                case "name":
                    String studentName = args[0];
                    studentToEdit.setRealName(studentName);
                    channel.sendMessage("Name successfully changed to: " + studentName);
                    break;
                case "school":
                    /**
                     * Use school call
                     */
                    if (!(studentToEdit.getSchool() == null)) {
                        int numberOfClasses = studentToEdit.getClasses().size();
                        if (numberOfClasses <= 0) {
                            if (Ryan.schools.containsKey(args[1])) {
                                School schoolToJoin = Ryan.schools.get(args[1]);
                                School schoolToRemoveStudent = studentToEdit.getSchool();

                                schoolToRemoveStudent.removeStudent(studentToEdit);

                                studentToEdit.setSchool(schoolToJoin);
                                studentToEdit.setGPA(0.0);
                                schoolToJoin.addStudent(studentToEdit);
                                channel.sendMessage("Sucessfully changed school to: " + schoolToJoin.getSchoolName())
                                        .queue();
                                channel.sendMessage(studentToEdit.toString()).queue();
                                ;

                            } else {
                                MessageOperations.invalidUsageShortner("https://google.com",
                                        "The school you are trying to switch to does not exist!", event.getMessage(),
                                        this);
                            }
                        } else {
                            MessageOperations.invalidUsageShortner("https://google.com", "You are enrolled in: "
                                    + numberOfClasses + "@ " + studentToEdit.getSchool().getSchoolName()
                                    + ", you cannot join a new school until your classes or finnished or you unenroll",
                                    event.getMessage(), this);
                        }
                    } else {
                        MessageOperations.invalidUsageShortner("https://google.com",
                                "You are not enrolled in a school, use ++joinschool <school call> to join a school!",
                                event.getMessage(), this);
                    }
                    break;
                case "gpa":
                    break;

            }

        }
    }
}