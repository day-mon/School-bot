package schoolbot.commands.school;

import java.util.InputMismatchException;
import java.util.HashMap;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolGirl;
import schoolbot.commands.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.InvalidUsage;

public class AddClass extends Command {

    /**
     * Adding a class will require a professor, credits, year, and a school that school will have to match the professor
     */


    public AddClass() {
        super(new String[] {"addclass"});  
    }

    public AddClass(String [] aliases) {
        super(aliases);
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
       MessageChannel channel = event.getChannel();
       
        /**
        * Args:
        * args[0] = ClassID ex. CS 0048 (String)
        * args[1] = ClassNum ex. 20937 (String)
        * args[2] = Time ex. MWF @ 12pm ()
        * args[3] = Year of coursE ex. Sophmore (Year of the class) (String)
        * args[4] = Credits ex. 3 (just the credit amount of the class)
        * args[5] = Professor ex. weierman (professor call not the professors name) (String)
        * args[6] = School (String)
        */

        /**
         * Check if the School and professor are first even valid entries.
         */

        if (args.length != 8) {
            channel.sendMessage(new InvalidUsage("https://google.com", "AddClass", "Too many args!", event.getMessage(), this).getInvalidUsage()).queue();
        } else if (!SchoolGirl.schools.containsKey(args[7])) {
            // invalid usage here.
            System.out.println(args[7]);
        } else {
            // TODO: finish
            Professor prof = null;
            boolean numeric = args[5].matches("-?\\d+(\\.\\d+)?");
   
            int credits = 0; // if credits is 0 on bot its because regex didnt work.
            for (Professor profs : SchoolGirl.professors) {
                if (profs.getLastName().equalsIgnoreCase(args[6])) {
                    prof = profs;
                }

            }

  


           if (numeric) {
                credits = Integer.parseInt(args[5]);
           } else {
                // invalid usage
                System.out.println("bad2");

           }

            if (prof == null) {
                // invalid usage
                System.out.println("bad3");

            }



            if (numeric && prof != null) {
             School schoolToAdd =  SchoolGirl.schools.get(args[7]);
                if (schoolToAdd.getListOfProfessors().containsKey(args[6])) {
                    Classroom classToAdd = new Classroom(args[0], args[1], args[2], args[3], args[4], credits, prof, schoolToAdd);
                    SchoolGirl.classes.put(args[2], classToAdd);
                    schoolToAdd.addClazz(classToAdd);
                    channel.sendMessage(":white_check_mark: Class added sucesfully :white_check_mark:").queue();
                } else {
                    channel.sendMessage(new InvalidUsage("https://google.com", "AddClass", "Professor is not at this school", event.getMessage(), this).getInvalidUsage()).queue();
                }

            }
      
        }

    }
    
    //TODO: do file
}