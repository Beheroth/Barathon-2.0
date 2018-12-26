import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Regroups elements to display a basic user interface.
 */
public class CheapInterface {

    /**
     * Default coordinate in case the user doesn't specify it.
     */
    private static final Double UNKNOWN_POSITION = 666.0;

    /**
     * Console object the CheapInterface uses to display outputs.
     */
    private Console c;

    /**
     * Scanner object the Cheapinterface uses to retrieve inputs.
     */
    private Scanner s;

    /**
     * List of known commands.
     */
    private ArrayList<String> commands;

    /**
     * Stores the User object to retrieve informations from.
     */
    private User currentUser;

    /**
     * Stores the Place object to retrieve informations from.
     */
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
     * @param console The Console object the CheapInterface shall use.
     */
    private void setC(final Console console) {
        this.c = console;
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
     * @param scanner The Scanner object the CheapInterface shall use.
     */
    private void setS(final Scanner scanner) {
        this.s = scanner;
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
    private void setCurrentUser(final User user) {
        this.currentUser = user;
        System.out.print(String.format("  User as been set to %s\n", currentUser.getPseudo()));
    }

    /**
     *  Gets the currentPlace attribute.
     *
     * @return currentPlace, the Place attribute.
     */
    public final Place getCurrentPlace() {
        return currentPlace;
    }

    /**
     *  Sets the currentPlace attribute.
     *
     * @param place the Place object the CheapInterface shall use.
     */
    public final void setCurrentPlace(final Place place) {
        this.currentPlace = place;
    }

    /**
     * Instantiate a CheapInterface object.
     */
    CheapInterface() {
        this.c = System.console();
        this.s  = new Scanner(System.in);
        this.commands = new ArrayList<String>();
        this.commands.add("NewUser");
        this.commands.add("LookAround");
    }

    /**
     *  Asks the user what he would like to do.
     */
    public final void askTask() {
        String input = "";
        String output = "What would you like to do: ";
        for (String command: commands) {
            output += command + " ";
        }
        System.out.print(output + "\n");
        input = s.next();
        input += s.nextLine();
        String command  = input;
        if (command.equals("NewUser")) {
            newUser();
        } else if (command.equals("LookAround")) {
            lookAround();
        } else {
            System.out.print(String.format("  Command unknown: %s \n", command));
        }
    }

    /**
     *  Asks and retrieve information to create a new User object.
     *  @return newly created User object.
     *
     *  TODO: Handle case where pseudo is already taken
     *  TODO: Handle case where input is not parsable to double
     *  TODO: Implement DBAccess
     */
    private User newUser() {
        String input = "";
        System.out.print("  --CREATING NEW USER--\n");
        System.out.print("    Please enter your username: ");
        input = s.next();
        input += s.nextLine();
        String pseudo = input;
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

            System.out.print("    Position (y): ");
            input = s.next();
            input += s.nextLine();
            y = new Double(input);

        } else {
            x = UNKNOWN_POSITION;
            y = UNKNOWN_POSITION;
        }
        Position pos = new Position(x, y);
        Preferences pref = this.newPreferences();
        User user = new User(pseudo, pos, pref);
        DBAccess.createUser(user);
        setCurrentUser(user);
        return user;
    }

    /**
     *  Asks and retrieve information to create a new Preference object.
     *
     * @return newly created Preference object.
     */
    private Preferences newPreferences() {
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
    private void lookAround() {
        if (getCurrentUser() == null) {
            newUser();
        }
        getCurrentUser().generate();
        getCurrentUser().show();
    }
}

