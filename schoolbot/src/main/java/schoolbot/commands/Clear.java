package schoolbot.commands;


import java.util.List;
import schoolbot.natives.util.operations.MessageOperations;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.natives.util.Command;

public class Clear extends Command {

    public Clear() {
        super(new String[] { "clear", "purge" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        int messagesToRemove = 99;
        Member userTyping = event.getMember();

        /**
         * Retrives the last 100 messages
         */
        List<Message> msg = event.getChannel().getHistory().retrievePast(messagesToRemove).complete();
        int actualMessagesInList = msg.size();
        /**
         * Purges chats with delay?
         */

        if (!userTyping.getPermissions().contains(Permission.ADMINISTRATOR)) {
            MessageOperations.invalidUsageShortner("https://google.com", "You don't have the wrong permissions!", event.getMessage(),
                    this);
            return;
        }

        if (actualMessagesInList <= 1) {
            channel.sendMessage("There is nothing to purge silly..").queue();
            return;
        }

        channel.purgeMessages(msg);

        channel.sendMessage("Commencing the purge").queue(response -> {
            response.editMessage("Purge completed!")
                    .queue(purgeMessage -> purgeMessage.delete().queueAfter(10, TimeUnit.SECONDS));
            return;
        });

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

        MessageChannel channel = event.getChannel();



        boolean numeric = args[0].matches("-?\\d+(\\.\\d+)?");
        if (numeric) {
            int messagesToRemove = Integer.parseInt(args[0]);
            if (messagesToRemove > 100) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "You cannot delete more than 100 messages at a time \n" + "Blame discord not me :(",
                        event.getMessage(), this);

                return;
            }

            /**
             * Retrives the last 100 messages
             */
            List<Message> msg = event.getChannel().getHistory().retrievePast(messagesToRemove).complete();

            /**
             * Purges chats with delay?
             */
            channel.purgeMessages(msg);


            //
            channel.sendMessage("Commencing the purge").queue(response -> {
                response.editMessage("Purge completed!")
                         .queue(purgeMessage -> purgeMessage.delete().queueAfter(10, TimeUnit.SECONDS));
                return;
            });

        } else {
            MessageOperations.invalidUsageShortner("https://google.com",
                    "'" + args[0] + "'" + " is not a not a number!", event.getMessage(), this);

        }

    }

}