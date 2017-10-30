package calendarReminders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Mr. Golanka on 3/30/16.
 * This class uses the Scanner class to read a filePath from the computer.
 * The class needs a "pathName" to the filePath--the address of the filePath on your computer
 * The pathName is sent to this class when it is turned into an object by another class.
 */
public class Read {
        String path;                                  //declare variable to hold pathName of filePath
        String content = "";                          //declare variable to hold filePath contents, set value to null string

        public void readFile() {               //Read each line of filePath using Scanner class, saving to a String variable
            File text = new File(path);        //create Java File variable that references the filePath on your computer
            Scanner scnr;                      //declare Scanner instance to read Java File

            try {
                scnr = new Scanner(text);
                while (scnr.hasNextLine()) {
                    String line = scnr.nextLine();
                    content = content + line + "\n";
                }//while
                scnr.close();
            } catch (FileNotFoundException e) {
                content = "Oops!\n" + e.toString();
            }//try-catch

        }//readFile

        public String getText(){
            return content;
        }//getText

    public Read(String p) {
        path = p;
    }//constructor
}//class
