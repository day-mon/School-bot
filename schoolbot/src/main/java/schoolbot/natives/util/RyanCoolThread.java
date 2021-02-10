package schoolbot.natives.util;

import java.io.Console;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import net.dv8tion.jda.api.entities.Role;
import schoolbot.Ryan;
import schoolbot.natives.Classroom;

public class RyanCoolThread implements Runnable {

    private boolean canRun = true;
    private long msWait = 5000;
    private int[] intervals = new int[] { 30, 10, 5 }; // minutes

    private HashMap<Classroom, int[]> flags = new HashMap<>();

    public RyanCoolThread() {
        // idk
    }

    @Override
    public void run() { // intervals: 0 = shortest, 1 = mid, 2 = longest
        try {
            do {
                Thread.sleep(msWait);
                for (Classroom c : Ryan.classes.values()) {
                    if (!flags.containsKey(c)) {
                        flags.put(c, new int[] { 0, 0, 0 });
                    }
                    int day = Ryan.today.getDayOfWeek().getValue();
                    String classType = c.getTime().split(" ")[0].toLowerCase();
                    int chosenInterval = 0;
                    int chosenIndex = 0;
                    long getTime = (StringOperations.formatClassTime(c.getTime()).getTime()) / 1000;
                    long _now = now();
                    long timeUntilClass = getTime - _now;
                    if (timeUntilClass < 0) {
                        continue;
                    }
                    for (int i = 0; i < intervals.length; i++) {
                        if (withinRange(timeUntilClass, (intervals[i] * 60) - (msWait / 1000),
                                (intervals[i] * 60) + (msWait / 1000))) {
                            chosenInterval = intervals[i];
                            chosenIndex = i;
                            break;
                        }
                    }
                    if (chosenInterval == 0 || flags.get(c)[chosenIndex] == 1) {
                        continue;
                    }
                    String chan = c.getTextChannel();
                    switch (classType) {
                        case "mwf":
                            if (day == 1 || day == 3 || day == 5) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "mtwf":
                            if (day == 1 || day == 2 || day == 3 || day == 5) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "tt":
                        case "tuthu":
                            if (day == 2 || day == 4) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "m":
                            if (day == 1) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "tu":
                            if (day == 2) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "w":
                            if (day == 3) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "thu":
                            if (day == 4) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        case "f":
                            if (day == 5) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                        default:
                            if (day == 6 || day == 7) {
                                msg(chan, c, chosenInterval);
                            }
                            break;
                    }
                    int[] _temp = flags.get(c);
                    _temp[chosenIndex] = 1;
                    flags.put(c, _temp);
                }
            } while (canRun);
        } catch (InterruptedException e) {

        } catch (ParseException e) {

            e.printStackTrace();
        }
    }

    public void msg(String chan, Classroom c, int interval) {
        String [] channelParsed = c.toString().split("\\-");
        if (c.getRole() == null) {
            for (Role roles : Ryan.jda.getRoles()) {
                String [] roleSplit = roles.getName().split("\s");
                if (roleSplit[roleSplit.length-1].equals(channelParsed[channelParsed.length-1])) {
                    c.setRole(roles);
                    break;
                }
            }
        }

        Ryan.jda.getTextChannelsByName(chan, true).get(0)
                .sendMessage((c.getRole() == null ? "@here " : c.getRole().getAsMention()) + " " + c.getClassName() + " starts in " + interval + " minutes!").queue();
        
    }

    public void msg(String chan, Classroom c, long interval) {
        String [] channelParsed = c.toString().split("\\-");
        if (c.getRole() == null) {
            for (Role roles : c.getGuild().getRoles()) {
                String [] roleSplit = roles.getName().split("\s");
                if (roleSplit[roleSplit.length-1].equals(channelParsed[channelParsed.length-1])) {
                    c.setRole(roles);
                    break;
                }
            }
        }



        Ryan.jda.getTextChannelsByName(chan, true).get(0)
                .sendMessage((c.getRole() == null ? "@here " : c.getRole().getAsMention()) + " " +  c.getClassName() + " starts in " + interval + " minutes!").queue();
    }
    public boolean withinRange(long a, long min, long max) {
        return (a <= max && a >= min);
    }

    public long now() {
        return System.currentTimeMillis() / 1000;
    }
}
