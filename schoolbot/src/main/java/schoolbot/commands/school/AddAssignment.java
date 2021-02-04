package schoolbot.commands.school;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.SchoolBot;
import schoolbot.commands.Command;

public class AddAssignment extends Command {

    public AddAssignment() {
        super(new String[] { "addassignment" }, "AddAssignment");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        // new invalid usage

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        /**
         * args[0] = class id args[1] = assignment name args[2] = due date (must be in
         * yyyy-MM-dd format) args[4] = points possible args[5] = assignment type
         */

        /**
         * TODO:
         */

        if (args.length != 6) {
            // new invalid usgae
        } else {
            if (SchoolBot.classes.containsKey(args[0])) {

            }
        }

    }

}
