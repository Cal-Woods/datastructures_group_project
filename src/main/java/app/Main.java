package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import utils.HashMap;
import java.util.Scanner;
import business.User;
import utils.*;

public class Main {
    //Class variables
    private static final String USER_STORE_DIR = "persistent-data";
    private static final String USER_STORE_FILE = USER_STORE_DIR+"/Users";
    public static void main(String[] args) throws IOException {
        HashMap users = initUsersMap(USER_STORE_DIR, USER_STORE_FILE);

        System.out.println(users.getCount());
}


    private static HashMap initUsersMap(String fileDir, String fileName) throws FileNotFoundException, IOException {
        //Validation
        if(fileDir == null) {
            throw new NullPointerException("Given fileDir was null! Program has must be restarteed!");
        }
        if(fileName == null) {
            throw new NullPointerException("Given fileName was null! Program must be restarted!");
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
        readUsers.useDelimiter(":");

        //Initialise while loop to iterate until there are no more tokens in Scanner
        while(readUsers.hasNext()) {
            temp.put(new User(readUsers.next(), readUsers.next()));
            readUsers.nextLine();
        }


        return temp;
    }
}
