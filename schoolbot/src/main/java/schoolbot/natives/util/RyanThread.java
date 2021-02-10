package schoolbot.natives.util;

import java.util.HashMap;

import schoolbot.Ryan;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;

public class RyanThread implements Runnable {

    private boolean canRun = true;
    private long msWait = 5000; // 5 seconds
    double[] intervals = new double[] { 12, 1, 0.5 }; // hours

    private HashMap<Assignment, int[]> flags = new HashMap<>();

    public RyanThread() {
        // idk
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(msWait);
                if (true) {
                    for (Classroom c : Ryan.classes.values()) {
                        if (c.getAssignments().size() > 0) {
                            for (Assignment a : c.getAssignments().values()) {
                                if (!flags.containsKey(a)) {
                                    flags.put(a, new int[] { 0, 0, 0 });
                                }
                                if (!a.isExpired()) {
                                    long timeDue = (a.getDueDate().getTime() / 1000) - now();
                                    if (timeDue <= 0) {
                                        Ryan.jda.getTextChannelsByName(c.getTextChannel(), true).get(0)
                                                .sendMessage(c.getRole()==null ? c.getRole().getAsMention() : "" + " " + a.getAssignmentName() + " just expired");
                                        a.setExpired(true);
                                        continue;
                                    }
                                    int chosenIndex = 0;
                                    double chosenInterval = 0;
                                    for (int i = 0; i < intervals.length; i++) {
                                        if (withinRange(timeDue, (intervals[i] * 3600) - ((double) msWait / 1000),
                                                (intervals[i] * 3600) + ((double) msWait / 1000))) {
                                            chosenIndex = i;
                                            chosenInterval = intervals[i];
                                            break;
                                        }
                                    }
                                    if (chosenInterval == 0 || flags.get(a)[chosenIndex] == 1) {
                                        continue;
                                    }
                                    Ryan.jda.getTextChannelsByName(c.getTextChannel(),
                                            true).get(
                                                    0)
                                            .sendMessage(c.getRole()==null ? c.getRole().getAsMention() : " "  + " " + a.getAssignmentName() + " is due in "
                                                    + (chosenInterval == 0.5 ? "30 minutes!"
                                                            : intervals[chosenIndex] + " hours!"))
                                            .queue();
                                    int[] _temp = flags.get(a);
                                    _temp[chosenIndex] = 1;
                                    flags.put(a, _temp);
                                }
                            }
                        }
                    }
                }
            } while (canRun);
        } catch (InterruptedException e) {

        }

    }

    public long now() {
        return System.currentTimeMillis() / 1000;
    }

    public void allow() {
        canRun = true;
    }

    public void cancel() {
        canRun = false;
    }

    public boolean withinRange(long a, double min, double max) {
        return (a <= max && a >= min);
    }
}
