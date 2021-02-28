package schoolbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.iwebpp.crypto.TweetNaclFast.Hash;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.operations.MessageOperations;

public class GungaLeaderboard extends Command {

    public GungaLeaderboard() {
        super(new String[] {"gleaderboard"});
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();    


        StringBuilder s = new StringBuilder("```Gunga Leaderboard \n");
        


        /*
        List<String> lists = new ArrayList<>(Ryan.gunga.keySet());
        Collections.sort(lists, Collections.reverseOrder());
        LinkedHashSet<String> keyset = new LinkedHashSet<>(lists);
        */
        
        for (String keys : Ryan.gunga.keySet()) {

            s.append(keys + "Gunga count: " + Ryan.gunga.get(keys) + "\n");

            if (s.length() >= 1750) {
                MessageOperations.messageExtender(s, channel);
            }
        }
        s.append("```");
        channel.sendMessage(s).queue();
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        // TODO Auto-generated method stub

    }

    /*
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> map) {

       HashMap<String, Integer> replaced = new HashMap<>();
       LinkedList<Integer> start = new LinkedList<>();
       
        for (Map.Entry<String, Integer> entry : Ryan.gunga.entrySet()) {
            start.add(entry.getValue());
        }

        Collections.sort(start);

        for (int num : start)
            for (Entry<String, Integer> entry : Ryan.gunga.entrySet()) {
                if (entry.getValue().equals(num)) {
                    replaced.put(entry.getKey(), num);
                }
                
            }
        }
        return replaced;
    }
    */
}
    
