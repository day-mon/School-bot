package schoolbot.natives.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import schoolbot.Ryan;
import schoolbot.commands.Command;

public class FileOperations {
    public static final File professor = new File("schoolbot\\src\\main\\files\\professors.ser");
    public static final File schools = new File("schoolbot\\src\\main\\files\\schools.ser");
    public static final File classes = new File("schoolbot\\src\\main\\files\\classes.ser");
    public static final File students = new File("schoolbot\\src\\main\\files\\students.ser");
    public static final File schoolsCalls = new File("schoolbot\\src\\main\\files\\schoolCalls.ser");

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
                System.out.println(objToWrite.getClass().getSimpleName() + " sucessfully written!");

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
                // TODO Auto-generated catch block
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

}
