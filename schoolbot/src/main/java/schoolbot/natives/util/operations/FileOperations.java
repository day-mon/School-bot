package schoolbot.natives.util.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Date;

import schoolbot.Ryan;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.Command;

public class FileOperations {
    public static final File professor = new File("schoolbot\\src\\main\\files\\professors.ser");
    public static final File schools = new File("schoolbot\\src\\main\\files\\schools.ser");
    public static final File classes = new File("schoolbot\\src\\main\\files\\classes.ser");
    public static final File students = new File("schoolbot\\src\\main\\files\\students.ser");
    public static final File schoolsCalls = new File("schoolbot\\src\\main\\files\\schoolCalls.ser");
    public static final File gunga = new File("schoolbot\\src\\main\\files\\gunga.ser");

    public static ArrayList<File> getAllFilesWithExt(File dir, String ext) {
        ArrayList<File> filesWithExt = new ArrayList<>();
        if (dir.isDirectory()) {
            if (dir.listFiles().length == 0)
                return null;
            for (File file : dir.listFiles()) {
                String[] fileParts = file.getName().split("\\.");
                int lastIndex = fileParts.length - 1;
                if (fileParts[lastIndex].equals(ext)) {
                    filesWithExt.add(file);
                }
            }
        } else
            return null;
        return filesWithExt;
    }

    public static void writeToFile(File dir, Object objToWrite) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dir);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

            oos.writeObject(objToWrite);
            oos.close();
            fileOutputStream.close();
            System.out.println(objToWrite.getClass().getSimpleName() + " sucessfully written!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(File[] dir, Object[] objToWrite) {

        if (dir.length != objToWrite.length)
            return;
        for (int i = 0; i < dir.length; i++) {
            try {
                FileOutputStream fos = new FileOutputStream(dir[i]);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(objToWrite);
                System.out.println(objToWrite.getClass().getPackageName()+ " sucessfully written!");

                if (i == dir.length - 1) {
                    fos.close();
                    oos.close();
                    return;
                }

                /**
                 * If files or something are not writing the last object or something remove -1.
                 */
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    public static ArrayList<String> parseDoc(Command com) {
        String className = com.getName();
        String name = className.substring(className.lastIndexOf(".") + 1);
        String relativePath = "schoolbot\\docs\\" + name + ".txt";

        File file = new File(relativePath);
        FileReader fileRead;
        BufferedReader reader = null;

        ArrayList<String> lines = new ArrayList<String>(); // or queue
        try {
            fileRead = new FileReader(file);
            reader = new BufferedReader(fileRead);
        } catch (FileNotFoundException fnfx) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#"))
                    lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeDocumentation(Command com) {
        String[] aliases = com.getCalls();
        String name = com.getName();
        File documentationFile = com.getDocumentationFile();

        if (documentationFile.exists()) {
            return;
        } else {
            try {
                PrintWriter write = new PrintWriter(documentationFile);
                write.print(name + "\n");
                for (int i = 0; i < aliases.length; i++) {
                    String comma = ", ";
                    if (i == aliases.length - 1) {
                        comma = "" + "\n";
                    }
                    write.print(Ryan.PREFIX + aliases[i] + comma);
                }
                write.print("PLACEHOLDER for FLAGS" + "\n");
                write.print("The `" + com.getName() + "` command" + "\n");
                write.print("PLACEHOLDER FOR EXAMPLE");

                write.close();
            } catch (FileNotFoundException e) {
                e.getLocalizedMessage();
            }

        }
    }

	@SuppressWarnings("unchecked")
    public static void loadFiles() {
        try {
            ArrayList<File> files = FileOperations.getAllFilesWithExt(new File("schoolbot\\src\\main\\files\\"), "ser");
            int serFiles = 0;
            try {
                serFiles = files.size();
            } catch (NullPointerException npex) {
                System.out.println("No files exist and serializer input failed.");
            }

            for (int i = 0; i < serFiles; i++) {
                FileInputStream fis = new FileInputStream(files.get(i).getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);

                String fileName = files.get(i).getName().split("\\.")[0];

                switch (fileName) {
                    case "schools":
                        Ryan.schools = (HashMap<String, School>) ois.readObject();
                        break;
                    case "schoolCalls":
                        Ryan.schoolCalls = (ArrayList<String>) ois.readObject();
                        break;
                    case "professors":
                        Ryan.professors = (HashMap<String, Professor>) ois.readObject();
                        break;
                    case "classes":
                        Ryan.classes = (HashMap<String, Classroom>) ois.readObject();
						break;
					case "gunga":
						Ryan.GUNGACOUNT = (Integer) ois.readObject();
						break;
					default:
						System.out.println(fileName + ".ser could not be loaded.");

				}

			}

		} catch (IOException | ClassNotFoundException e) {
        }
        ;


     
    }

}
