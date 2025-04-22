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

        //Start of program
        System.out.println("Welcome to the IT ticket management program! Once logged in, you may submit a ticket to the application, or if you are an Agent, you may manage tickets.\n");
        
        //Print message
        System.out.println("\nYou must login before you can access this application. Choose to login or register below.");

        //Declare user
        User authenticatedUser = null;

        //Declare running boolean
        boolean running = true;

        //Declare Scanner keyboard
        Scanner keyboard = new Scanner(System.in);

        //Initialise while loop
        while (running) {
            //Print options
            System.out.println("Please choose an option?");

            System.out.print("1) Login(If no users are registered, you will have to register)\n2) Register\nOption: ");

            //Capture input
            String option = keyboard.next();
            
            //Clear keyboard Scanner
            keyboard.nextLine();

            //Validate option
            if(option.equals(1)) {
                authenticatedUser = loginSys(users);
            }
            else if(option.equals(2)) {
                //TODO: make registration system
                throw new UnsupportedOperationException("Registration feature coming soon...");
            }
            else {
                running = false;
            }
        }

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
            throw new NullPointerException("Given fileDir was null! Program must be restarted!");
        }
        if(fileName == null) {
            throw new NullPointerException("Given fileName was null! Program must be restarted!");
        }
        if(fileDir.isBlank()) {
            throw new IllegalArgumentException("Given directory path must NOT be empty!");
        }
        if(fileDir.isBlank()) {
            throw new IllegalArgumentException("Given file name must NOT be empty!");
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
        while(readUsers.hasNextLine()) {
            String username = readUsers.next();
            String secret = readUsers.next();

            temp.put(username, new User(username, secret));

            readUsers.nextLine();
        }

        readUsers.close();

        return temp;
    }

    /**
     * A login form that handles capturing username & password from the user and comparing them to a given HashMap of User objects.
     * @param users Given HashMap
     * @return A HashMap of User objects read from a file, or a blank HashMap if users file was empty
     * @author Cal Woods
     */
    private static User loginSys(HashMap users) {
        //Validation
        if(users == null) {
            return null;
        }
        if(users.getCount() == 0) {
            System.out.println("\nThere are no registered users. Please register and come back.");

            return null;
        }

        //Declare scanner with System.in arg
        Scanner scanner = new Scanner(System.in);

        //Declare incorrect attempts counter
        int incorrectAttempts = 0;

        while(incorrectAttempts < 3) {
            //Print prompt
            System.out.print("\nUsername(Spaces will be ignored): ");

            //Store username as scanner token
            String username = scanner.next();

            //Clear scanner buffer
            scanner.nextLine();

            //Repeat for password
            System.out.print("Password: ");

            String password = scanner.nextLine();
            
            //Get matching User object
            User match = users.get(username);

            //Check if there was a match
            if(match == null) {
                System.out.println("\nUsername was not found in the database! Please try again.");
            }

            //Compare captured username & password to match
            else {
                if(password.equals(match.getPassword())) {
                    return match;
                }
                else {
                    //Increment incorrectAttempts by 1
                    incorrectAttempts++;

                    System.out.println("\nThe given password was incorrect! Please enter your credentials again...");
                }
            }
        }

        System.out.println("\nThere have been too many incorrect attempts! Please try to login again.");

        return null;
    }
}
