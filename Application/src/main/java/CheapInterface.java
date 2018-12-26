import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CheapInterface {

    private static final Double UNKNOWN_POSITION = 666.0;

    private Console c;
    private Scanner s;
    private ArrayList<String> commands;

    private User currentUser;
    private Place currentPlace;

    /**
     *  Gets the Console attribute.
     *
     * @return c, the console attribute.
     */
    private Console getC() {
        return c;
    }

    /**
     *  Sets the Console attribute.
     *
     * @param c The Console object the CheapInterface shall use.
     */
    private void setC(Console c) {
        this.c = c;
    }

    /**
     *  Gets the Scanner attribute.
     *
     *  @return s, the Scanner attribute.
     */
    private Scanner getS() {
        return s;
    }

    /**
     *  Sets the Scanner attribute.
     *
     * @param s The Scanner object the CheapInterface shall use.
     */
    private void setS(Scanner s) {
        this.s = s;
    }

    /**
     *  Gets the currentUser attribute.
     *
     * @return currentUser, the User attribute.
     */
    private User getCurrentUser() {
        return currentUser;
    }

    /**
     *  Sets the currentUser attribute.
     *
     * @param user The User object the CheapInterface shall use.
     */
    private void setCurrentUser(User user) {
        this.currentUser = user;
        System.out.print(String.format("  User as been set to %s\n", currentUser.getPseudo()));
    }

    /**
     *  Gets the currentPlace attribute.
     *
     * @return currentPlace, the Place attribute.
     */
    public Place getCurrentPlace() {
        return currentPlace;
    }

    /**
     *  Sets the currentPlace attribute.
     *
     * @param place the Place object the CheapInterface shall use.
     */
    public void setCurrentPlace(Place place) {
        this.currentPlace = place;
    }

    /**
     * Regroups elements to display a basic user interface.
     */
    CheapInterface(){
        this.c = System.console();
        this.s  = new Scanner(System.in);
        this.commands = new ArrayList<String>();
        this.commands.add("NewUser");
        this.commands.add("LookAround");
    }

    /**
     *  Asks the user what he would like to do.
     */
    public void ask_task(){
        String input = "";
        String output = "What would you like to do: ";
        for (String command: commands) {
            output += command + " ";
        }
        System.out.print(output + "\n");
        input = s.next();
        input += s.nextLine();
        String command  = input;
        if (command.equals("NewUser")){
            newUser();
        } else if (command.equals("LookAround")){
            lookAround();
        } else {
            System.out.print(String.format("  Command unknown: %s \n", command));
        }
    }

    /**
     *  Asks and retrieve information to create a new User object.
     */
    private User newUser() {
        String input = "";
        System.out.print("  --CREATING NEW USER--\n");
        System.out.print("    Please enter your username: ");
        input = s.next();
        input += s.nextLine();
        String pseudo = input;
        //TODO: Handle case where pseudo is already taken
        System.out.print("    ! Can we use your actual position ? [Y/n] :");
        input = s.next();
        input += s.nextLine();

        boolean bpos = "Yy ".indexOf(input) >= 0;
        Double x = 0.0;
        Double y = 0.0;
        if (bpos) {
            System.out.print("    Position (x): ");
            input = s.next();
            input += s.nextLine();
            x = new Double(input);
            //TODO: Handle case where input is not parsable to double

            System.out.print("    Position (y): ");
            input = s.next();
            input += s.nextLine();
            y = new Double(input);
            //TODO: Handle case where input is not parsable to double

        } else {
            x = UNKNOWN_POSITION;
            y = UNKNOWN_POSITION;
        }
        Position pos = new Position(x, y);
        Preferences pref = this.newPreferences();
        User user = new User(pseudo, pos, pref);
        //DBAccess.createUser(user);
        //TODO: Implement DBAccess
        setCurrentUser(user);
        return user;
    }

    /**
     *  Asks and retrieve information to create a new Preference object.
     */
    private Preferences newPreferences(){
        System.out.println("\n --- Set your Preferences --- \n");
        System.out.print("    - Cheap [Y/n]: ");

        String input = s.next();
        input += s.nextLine();
        boolean cheap = "Yy ".indexOf(input) >= 0;
        System.out.print("    - Music [Y/n]: ");
        //String music = c.readLine();
        input = s.next();
        input += s.nextLine();
        boolean music = "Yy ".indexOf(input) >= 0;
        System.out.print("    - Alcohol [Y/n]: ");
        //String alcohol = c.readLine();
        input = s.next();
        input += s.nextLine();
        boolean alcohol = "Yy ".indexOf(input) >= 0;

        System.out.println(String.format("\n    You chose : %s %s %s \n",
                cheap, music, alcohol));
        Caracteristics car = new Caracteristics();
        car.setCheap(cheap);
        car.setMusic(music);
        car.setAlcohol(alcohol);

        System.out.println("\n --- Choose a Type of Search --- \n");
        System.out.print("  - Trip [Y/n]: ");
        //String trip = c.readLine();
        input = s.next();
        input += s.nextLine();
        boolean trip = "Yy ".indexOf(input) >= 0;
        System.out.print("  - Radius: ");
        //String trip = c.readLine();
        input = s.next();
        input += s.nextLine();
        int radius = Integer.parseInt(input);

        System.out.println(String.format("\n  You chose : %s %d\n",
                trip, radius));
        Preferences pref = new Preferences();
        pref.addCaracteristics(car);
        pref.setTrip(trip);
        pref.setRadius(radius);

        return pref;
    }

    /**
     * Displays bars following the currentUser's preferences.
     */
    private void lookAround(){
        if(getCurrentUser() == null){
            newUser();
        }
        getCurrentUser().generate();
        getCurrentUser().show();
    }
}

