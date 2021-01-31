package schoolbot.natives.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileOperations {

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeToFile(File[] dir, Object[] objToWrite) {
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
            }
        }
    }

}
