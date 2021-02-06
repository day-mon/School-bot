package schoolbot.natives.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import schoolbot.Ryan;
import schoolbot.natives.Classroom;

public class RyanCoolThread implements Runnable {

    private boolean canRun = true;
    private long msWait = 5000;
    private int[] intervals = new int[] { 30, 10, 5 };

    public RyanCoolThread() {
        // idk
    }

    @Override
    public void run() { // intervals: 0 = shortest, 1 = mid, 2 = longest
        try {
            do {
                Thread.sleep(msWait);
                for (Classroom c : Ryan.classes.values()) {
                    int day = Ryan.today.getDayOfWeek().getValue();
                    String classType = c.getTime().split(" ")[0].toLowerCase();
                    int chosenInterval = 0;
                    long timeUntilClass = (StringOperations.formatClassTime(c.getTime()).getTime() / 1000) - now();
                    if (timeUntilClass < 0) {
                        continue;
                    }
                    for (int i = 0; i < intervals.length; i++) {
                        if (withinRange(timeUntilClass, (intervals[i] * 60) - msWait, (intervals[i] * 60) + msWait)) {
                            chosenInterval = intervals[i];
                            break;
                        }
                    }
                    String chan = "testing-grounds";
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
                            break;
                    }
                }
            } while (canRun);
        } catch (InterruptedException e) {

        } catch (ParseException e) {

            e.printStackTrace();
        }
    }

    public void msg(String chan, Classroom c, int interval) {
        Ryan.jda.getTextChannelsByName(chan, true).get(0)
                .sendMessage(c.getClassName() + " starts in " + interval + " minutes!").queue();
    }

    public boolean withinRange(long a, long min, long max) {
        return (a <= max && a >= min);
    }

    public long now() {
        return System.currentTimeMillis() / 1000;
    }
}
