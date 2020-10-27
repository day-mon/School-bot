package schoolbot;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.entities.GuildImpl;

public class Student extends net.dv8tion.jda.internal.entities.MemberImpl {

    private School mySchool;
    private ArrayList<Classroom> myClasses;

    public Student(GuildImpl guild, User user) {
        super(guild, user);
    }
    

}
