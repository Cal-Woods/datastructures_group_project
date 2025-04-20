package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import utils.HashMap;
import java.util.Scanner;
import business.User;

public class Main {
    //Class variables
    private static final String USER_STORE_DIR = "persistent-data";
    private static final String USER_STORE_FILE = USER_STORE_DIR+"/Users";
    
    public static void main(String[] args) throws IOException {
        HashMap users = initUsersMap(USER_STORE_DIR, USER_STORE_FILE);
        //TODO - Collins, Jerome: Add data structures for storing open tickets, closed tickets: PriorityQueue, LinkedList
}

    /**
     * Initialises application HashMap by reading a file from a given directory path & a file name.
     * @param fileDir Given directory path, can NOT be empty or null
     * @param fileName Given file name:Must be full path, can NOT be empty or null
     * @return A HashMap filled with Users if file found & not empty, otherwise a blank HashMap
     * @throws FileNotFoundException If trying to access a file that doesn't exist
     * @throws IOException If caller does NOT have appropriate permissions to create files
     * 
     * @author Cal Woods
     */
    private static HashMap initUsersMap(String fileDir, String fileName) throws FileNotFoundException, IOException {
        //Validation
        if(fileDir == null) {
            throw new NullPointerException("Given fileDir was null! Program has must be restarteed!");
        }
        if(fileName == null) {
            throw new NullPointerException("Given fileName was null! Program must be restarted!");
        }
        if(fileDir.isBlank()) {
            throw new IllegalArgumentException("Given directory path must NOT be empty!")
        }
        if(fileDir.isBlank()) {
            throw new IllegalArgumentException("Given file name must NOT be empty!")
        }

        //Declare HashMap temp as future return type
        HashMap temp = new HashMap();

        //Declare references to file path for storing users
        File usersDir = new File(fileDir);
        File usersFile= new File(fileName);

        if(!usersFile.exists()){
            //Create directory
            usersDir.mkdir();
            //Create specified file if it does not exist
            usersFile.createNewFile();
        }

        //Check if file is empty
        if(usersFile.length() == 0.00) {
            return temp;
        }
        
        //Set-up  Scanner  and set delimiter
        Scanner readUsers = new Scanner(usersFile);
        readUsers.useDelimiter(":|\n");

        //Initialise while loop to iterate until there are no more tokens in Scanner
        while(readUsers.hasNext()) {
            String username = readUsers.next();
            String secret = readUsers.next();

            temp.put(new User(username, secret));
        }

        readUsers.close();

        return temp;
    }
}
