package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.commands.Command;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.FileOperations;
import schoolbot.natives.util.MessageOperations;
import schoolbot.natives.util.StringOperations;

public class EditProfessor extends Command {

    public EditProfessor() {
        super(new String[] {"editprofessor", "profedit"});
    }
 
    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        /**
         * Stuff just for 
         */
        Message msg = event.getMessage();
        Command com = this;

        MessageChannel channel = event.getChannel();

        if (args.length < 3) {
            String schoolArgs = args[0];
            if (Ryan.schools.containsKey(schoolArgs)) {
                School school = Ryan.schools.get(schoolArgs);
                String professorReference = args[1];
                if (school.getListOfProfessors().containsKey(professorReference)) {
                    String attributeToEdit = args[2];
                    Professor professorToEdit = school.getListOfProfessors().get(professorReference);
                    switch (attributeToEdit) {
                        case "firstname":
                            String firstName = args[3];
                            professorToEdit.setfirstName(firstName);
                            channel.sendMessage("Professors first name successfully changed to: " + firstName);
                        break;
                        case "lastname":
                            String lastName = args[3];
                            professorToEdit.setLastName(lastName);
                            channel.sendMessage("Professors first name successfully changed to: " + lastName);                            
                        break;
                        case "email":
                            String emailToEdit = args[3];
                            String oldProfessorEmailPrefix = professorToEdit.getEmailPrefix();
                            Professor newProfessor = professorToEdit;

                            newProfessor.setEmailPrefix(emailToEdit);
                            school.getListOfProfessors().put(emailToEdit, professorToEdit);
                            school.getListOfProfessors().remove(oldProfessorEmailPrefix);

                            channel.sendMessage("Professors email prefix successfully changed to: " + emailToEdit);
                        break;
                        case "school":
                            String schoolReference = args[3];
                            if (professorToEdit.getProfessorsClasses().size() > 0 ) {
                                if (Ryan.schools.containsKey(schoolReference)) {
                                    School schoolProfessorSwitch = Ryan.schools.get(schoolReference); 

                                    
                                } else {
                                    MessageOperations.invalidUsageShortner("https://google.com",  schoolReference + " is not a valid school reference!", msg, com);
                                    return;
                                }
                            } else {
                                MessageOperations.invalidUsageShortner("https://google.com", "Professor has classes at this school!", msg, com);
                                return;
                            }
                        break;
                        case "age":
                            String number = args[3];
                            boolean numeric = StringOperations.numericCheck(number);
                            if (numeric) {
                                int numericInt = Integer.parseInt(number);
                                professorToEdit.setAge(numericInt);
                                channel.sendMessage(professorToEdit.getFirstName() + "'s age changed to: " + numericInt);
                            } else {
                                MessageOperations.invalidUsageShortner("https://google.com", "That is not a valid number!", msg, com);
                                return;
                            }
                        break;
                        case "officehours":
                            String officehours = args[3];
                            professorToEdit.setOfficeHours(officehours);
                            channel.sendMessage("Office hours successfully changed!").queue();
                        break;
                    }
                    FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
                    FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", "Professor doesnt goto this school", msg, com);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "School doesnt exist!", msg, com);
            }
        } else {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 3 args", msg, com);
        } 
    }
    
}
