package calendarReminders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Writer {
    String pathName;
    Path filePath;

    public String writeToFile(String fileContent){
        String result = "File written successfully.";
        try {
            Files.write(filePath,fileContent.getBytes());          //getBytes() means this is writing one byte at a time
        } catch (IOException e) {
            result = "Failed to write:\n" + e.getMessage();
            e.printStackTrace();
        }//try-catch
        return result;
    }//writeToFile

    public Writer(String p){
        pathName = p;
        filePath = Paths.get(pathName);
    }//constructor
}//class
