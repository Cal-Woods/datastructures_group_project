package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//Our code
import business.*;
import utils.*;

public class  Main {
    //Class variables
    private static final String USER_STORE_DIR = "persistent-data";
    private static final String USER_STORE_FILE = USER_STORE_DIR+"/Users";
    private static final String TICKET_STORE_FILE = USER_STORE_DIR+"/Tickets";

    private static PriorityQueue openTicketsQueue = new PriorityQueue();



    //Declare authenticatedUser
    private static User authenticatedUser = null;
    public static void main(String[] args) throws IOException {
        HashMap users = initUsersMap(USER_STORE_DIR, USER_STORE_FILE);
        LinkedList closedTickets = new LinkedList();

        //Start of program
        System.out.println("Welcome to the IT ticket management program! Once logged in, you may submit a ticket to the application, or if you are an Agent, you may manage tickets.\n");
        
        //Print message
        System.out.println("\nYou must login before you can access this application. Choose to login or register below.");

        //Declare running boolean
        boolean running = true;

        //Declare Scanner keyboard
        Scanner keyboard = new Scanner(System.in);

        //Initialise while loop
        while (running) {
            //If not authenticated
            if(authenticatedUser == null) {
                //Print options
                System.out.println("\nPlease choose an option?");

                System.out.print("1) Login(If no users are registered, you will have to register)\n2) Register\nAny other key to exit\nOption: ");

                //Capture input
                String option = keyboard.next();
                
                //Clear keyboard Scanner
                keyboard.nextLine();

                //Declare boolean to confirm registration status
                boolean registerSuccess = false;

                //Validate option
                if(option.equals("1")) {
                    authenticatedUser = loginSys(users);
                }
                else if(option.equals("2")) {
                    registerSuccess = registerSys(users);
                }
                else {
                    System.out.println("\nYou have typed "+option+". Exiting the application...");
                    running = false;
                }
        }


        
            //Check if authenticatedUser is NOT null
            else if(authenticatedUser !=  null) {
                //TODO - Add all logic here

                System.out.println("\nWelcome "+authenticatedUser.getUsername()+"!\nPlease choose from the list of menu options:\n1)Create a new ticket\n2)Remove a ticket\n3)Change ticket status");

                String menuChoice = keyboard.nextLine();

                //Validate menuChoice
                if(menuChoice.equals("1")) {
                    OpenTicket(keyboard);
                }
                else if(menuChoice.equals("2")) {
                    System.out.print("\nChoose a ticket number to remove(Must be between 0 and size of list - 1, size = "+closedTickets.size()+"):");

                    try {
                        int remove = keyboard.nextInt();
                    }
                    catch(InputMismatchException e) {
                        System.out.println("\nThat is not a number!");
                    }

                    //throw new UnsupportedOperationException("Coming soon...");
                }
                else if(menuChoice.equals("3")) {
                    throw new UnsupportedOperationException("Coming soon...");
                }

                return;
            }
        }
    }
    /**
     * allows the user to submit a support ticket  which adds to the PriorityQueue
     * the support ticket should have ticket description,priority level which goes from 1 to 5.
     * @param authenticateUser the authenticated user submitting the ticket.
     * @param keyboard scanner for reading the input
     * author Jerome
     */

    private static void OpenTicket(Scanner keyboard) {
        //welcomes the user
        System.out.println("Welcome," + authenticatedUser.getUsername());
        //should ask ticket details
        System.out.println("Submit a ticket below");

        // the ticket should ask for ticket description
        System.out.print("Enter ticket description: ");
        String ticketDescription = keyboard.nextLine();
        //the ticket should ask for a priority value(1-5)
        int priority = 0;
        while (priority < 1 || priority > 5) {
            System.out.print("Enter ticket priority(1 =low, 5=high): ");

            /**
             * the method should allow an integer only!(1-5)
             * @ throws NumberFormatException if input is not a valid integer i.e 4.5
             * author Jerome
             */
            try{
                priority = Integer.parseInt(keyboard.nextLine());
                if(priority < 1 || priority > 5) {
                    System.out.println("Ticket priority must be between 1 and 5.");
                }
            }catch(NumberFormatException e) {
                System.out.println("Invalid number.Enter a priority between 1 and 5.");
            }
        }
        /**
         * generate a new ticket ID
         */
        int ticketId = new Random().nextInt();
        /**
         * method displays a ticket with details
         * @param ticketId an ID for the ticket
         * @param ticketDescription ticket with its description
         * @param priority the priority has to be between (1-5)
         * @param user.getUsername the username of the reporting user
         * author Jerome*/
        Ticket ticket = new Ticket(ticketId,ticketDescription,priority,authenticatedUser.getUsername());
        /**
         * Adds the created ticket in the PriorityQueue of open ticket*/
        openTicketsQueue.enqueue(ticket);

        System.out.println("Ticket submitted successfully" + openTicketsQueue.peek());
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

    /**
     * Registration system for users & agents.
     * 
     * @param compare Given HashMap
     * 
     * @return True if registration successful.
     * 
     * @auth
     *  Cal Woods
     */
    private static boolean registerSys(HashMap compare) throws FileNotFoundException, IOException{
        //Declare Scanner set to System.in
        Scanner scanner = new Scanner(System.in);

        //Print message
        System.out.println("\nRegister below, We will require a username and password. Here are some things to note:\n  Username must be unique\n  Password must be at least 8 characters in length\nBasic user or agent");

        //Print prompt
        System.out.print("\nWould you like to register as a user or agent('user'/'agent' without quotes): ");

        //Store input
        String input = scanner.next();

        //Clear scanner buffer
        scanner.nextLine();

        //Print message
        System.out.println("\nYou will be required to provide a new username & password.");

        //Print prompt
        System.out.print("\nUsername(No spaces allowed): ");
        //Store input
        String username = scanner.next();

        scanner.nextLine();

        //Repeat
        System.out.print("Password: ");

        String password = scanner.nextLine();

        System.out.print("Re-enter password: ");
        String confirmPass = scanner.nextLine();

        //Validate password
        while(!password.equals(confirmPass)) {
            System.out.println("Password do not match! They must match!");

            System.out.print("\nPlease re-enter password!: ");
            password = scanner.nextLine();
            System.out.print("Confirm password: ");
            confirmPass = scanner.nextLine();
        }

        while(password.length() < 8) {
            System.out.println("Password cannot be less than 8 characters!");

            System.out.print("\nPlease re-enter password!: ");
            password = scanner.nextLine();
            System.out.print("Confirm password: ");
            confirmPass = scanner.nextLine();
        }

        if(input.equalsIgnoreCase("agent")) {
            System.out.println("\nWe will require your name as you are an agent. If you do not consent, Type 'N', and you will be registered as a basic User, otherwise, type 'continue'):");

            System.out.print("\nConsent: ");
            String consent = scanner.next();

            scanner.nextLine();


            //While consent NOT equals n or continue
            while(!consent.equalsIgnoreCase("n") && !consent.equalsIgnoreCase("continue")) {
                    //Get consent again
                    System.out.println("\nYou typed "+consent+" which is not a valid consent option! Please type either 'n' or 'continue");

                    System.out.print("\nConsent: ");

                    consent = scanner.next();

                    //Clear scanner token
                    scanner.nextLine();
            }

            //Check consent
            if(consent.equalsIgnoreCase("n")) {
                System.out.println("\nYou have indicated that you would not like to give a name. Registering you as a basic User!");
                //Put new User() into given HashMap
                compare.put(username, new User(username, password));

                //Store user in file
                storeUser(USER_STORE_DIR, USER_STORE_FILE, authenticatedUser);

                return true;
            }
            else {
                System.out.print("\nPlease enter a name: ");
                String name = scanner.nextLine();

                //random number generator to generate agent id
                Random rand = new Random();

                //Number to multiply rand, big to mitigate collisions
                final int MAX_USERS = 2500;

                //Declare new Agent object set to given details
                Agent agent = new Agent(username, password, rand.nextInt(MAX_USERS), name);

                //Add to users HashMap
                compare.put(username, agent);

                //Call storeUser() to store new user in file
                storeUser(USER_STORE_DIR, USER_STORE_FILE, agent);
            }
        }
        else if(input.equalsIgnoreCase("user")) {
            //Place new User in given HashMap compare
            User userObj = new User(username, password);

            compare.put(username, userObj);

            storeUser(USER_STORE_DIR, USER_STORE_FILE, userObj);
        }
        //Store new User/Agent in filePath
        return true;
    }

    /**
     * Stores Users including Agents in a file with the given file path.
     * 
     * @param filePath Given file path
     * @param dirPath Given Directory path
     * 
     * @author Cal Woods
     * 
     * @see Note To be used as helper for registerSys() method.
     */
    private static boolean storeUser(String dirPath, String filePath, User user) throws FileNotFoundException, IOException{
        //validation
        if(filePath == null) {
            //Print error message
            System.err.println("\nGiven file path was null!");

            return false;
        }
        if(dirPath == null) {
            //Print error message
            System.err.println("\nGiven file path was null!");

            return false;
        }

        if(user == null) {
            System.err.println("Given User object was null which is NOT allowed!");

            return false;
        }

        //Declare two File objects
        File directory = new File(dirPath);
        File file = new File(filePath);

        //Check if given file path exists
        if(!file.exists()) {
            //Create fiile
            directory.mkdir();
            file.createNewFile();
        }

        //Declare FileWriter to write to file
        FileWriter write = new FileWriter(file, true);

        write.write((user.getUsername()+":"+user.getPassword()+":"));
        //Check user type
        if(user instanceof Agent) {
            write.write((((Agent) user).getAgentName()+":"+((Agent) user).getAgentID()+":"));
        }

        write.close();
        return true;
    }
}