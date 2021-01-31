package schoolbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

// Do not edit

public class Chousei {


    /** Druecke einige Aufgaben zur Konsole dass wir bald reparieren sollen.
     * @param DateiPfad Pfad zur Datei von der die koordinierte Aufgaben kommen
     * @author Takuma Akimori
     */
    public static void tasks(String username){
        String dateiPfad = username.charAt(0) != 'd' ? "G:\\DiscordBots\\SchoolGirl\\schoolbot\\src\\main\\files\\tasks.chousei.txt"
                 : "C:\\Users\\damon\\BotForSchool\\School-Bot\\schoolbot\\src\\main\\files\\tasks.chousei.txt";
        tasks(username, dateiPfad);
    }

    public static void tasks(String username, String DateiPfad){
        File file = new File(DateiPfad);
        System.out.println("Tasks:\n");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
              System.out.println(" + "+line);
            }
        }catch(IOException iox){
            System.out.println("Datei existiert nicht.");
        }
        System.out.print("\n\n");
    }
}
