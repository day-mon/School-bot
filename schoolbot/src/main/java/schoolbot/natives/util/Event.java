package schoolbot.natives.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Event {
    
    protected String[] triggers;
    protected String name;

    public Event(String [] triggers) 
    {
        for (int i = 0; i <  triggers.length; i++) 
            this.triggers[i] = triggers[i];
        this.name = this.getClass().getSimpleName();
    }

    public abstract void run(MessageReceivedEvent event);

    public String[] getTriggers() {
        return this.triggers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
