package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.School;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;

public class EditSchool extends Command {

    public EditSchool() {
        super(new String [] {"editschool", "schooledit", "schoole"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 3 arguments!", event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Message msg = event.getMessage();
        Command com = this;
        MessageChannel channel = event.getChannel();
        /**
         * args[0] = school
         * args[1] = thing to edit
         * args[2] = edit
         */

        if (args.length >= 3 ) {
            String schoolReference = args[0];
            if (Ryan.schools.containsKey(schoolReference)) {
                String attributeEdit = args[1];
                School schoolToEdit = Ryan.schools.get(attributeEdit);

                switch (attributeEdit) {
                    case "name": 
                        boolean isAlphabetic = name.chars().allMatch(Character::isAlphabetic);
                        String name = args[2];

                        if (isAlphabetic) {
                            schoolToEdit.setSchoolName(name);
                            channel.sendMessage("School name successfully changed!").queue();
                        } else {
                            MessageOperations.invalidUsageShortner("https://google.com", "That school contains non-alphabetic characters!", msg, com);
                        }
                        break;
                    case "email":
                        String emailEdit = args[2];
                        schoolToEdit.setEmailSuffix(emailEdit);      
                        channel.sendMessage("Email suffix successfully changed!").queue();
                  
                    break;
                }
                FileOperations.writeToFile(FileOperations.schools, Ryan.schools);

            } else {
                MessageOperations.invalidUsageShortner("https://google.com", schoolReference + " is not a valid school reference!", msg, com);
            }
        } else {
            MessageOperations.invalidUsageShortner("https://google.com", "You are missing arguments, this command takes in atleast 3 arguments!", msg, com);
        }

    }
    
}
