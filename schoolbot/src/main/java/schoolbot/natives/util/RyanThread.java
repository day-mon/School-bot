package schoolbot.natives.util;

import schoolbot.Ryan;
import schoolbot.natives.Assignment;
import schoolbot.natives.Classroom;

public class RyanThread implements Runnable {
    
    private boolean canRun = true;
    private long time;
    private long msWait = 5000; //5 seconds

    public RyanThread(long startTime) {
        time = startTime;
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
                                if (!a.isExpired()) {
                                    long timeDue = (a.getDueDate().getTime() / 1000) - now();
                                    if (timeDue <= 0) {
                                        System.out.println("X is due now !!!");
                                        a.setExpired(true);
                                        continue;
                                    }
                                    if (a.getLdt().getDayOfYear() == Ryan.today.getDayOfYear() && timeDue <= Ryan.onehour) {
                                        System.out.println(a.getAssignmentName() + " is due in " + timeDue + " seconds");
                                        Ryan.jda.getTextChannelsByName("testing-grounds", true).get(0).sendMessage(a.getAssignmentName() + " is due in " + 
                                        StringOperations.formatTime(timeDue)).queue();
                                        //Ryan.tc.sendMessage(a.getAssignmentName() + " is due in " + formatTime(timeDue)).queue();
                                    }
                                }
                            }
                        }
                    }
                }
            } while (canRun);
        } catch (InterruptedException e) {

        }

    }

    public long now(){
        return System.currentTimeMillis() / 1000;
    }

    public void allow() {
        canRun = true;
    }

    public void cancel() {
        canRun = false;
    }
}
