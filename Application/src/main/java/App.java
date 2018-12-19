
import java.io.Console;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

/**
 *  Class to have an inteface between the application and the user.
 */
public final class App {

    /**
    * Private constructor.
    **/
    private App() {
    }

    /**
     * When the user position is unknown, the coordinates are (666,666).
    **/
    private static final Double UNKNOWN_POSITION = 666.0;

    private static User cheapInterface(){
        Console c = System.console();
        Scanner scanner  = new Scanner(System.in);

        String input = "";
        System.out.print("  Please enter your username: ");
        input = scanner.next();
        input += scanner.nextLine();
        String pseudo  = input;
        System.out.println(String.format("  Hello %s !\n", pseudo));

        System.out.print("  ! Can we use your actual position ? [Y/n] :");
        input = scanner.next();
        input += scanner.nextLine();

        boolean bpos = "Yy ".indexOf(input) >= 0;
        Double x = 0.0;
        Double y = 0.0;
        if (bpos) {
            System.out.print("  Position (x) : ");
            input = scanner.next();
            input += scanner.nextLine();
            x = new Double(input);

            System.out.print("  Position (y) : ");
            input = scanner.next();
            input += scanner.nextLine();
            y = new Double(input);
        } else {
            x = UNKNOWN_POSITION;
            y = UNKNOWN_POSITION;
        }
        System.out.println(String.format("\n  Your position : %f %f \n", x, y));
        Position pos = new Position(x, y);

        System.out.println("\n --- Choose Preferencies --- \n");
        System.out.print("  - Cheap [Y/n]: ");
        //String cheap =
        input = scanner.next();
        input += scanner.nextLine();
        boolean cheap = "Yy ".indexOf(input) >= 0;
        System.out.print("  - Music [Y/n]: ");
        //String music = c.readLine();
        input = scanner.next();
        input += scanner.nextLine();
        boolean music = "Yy ".indexOf(input) >= 0;
        System.out.print("  - Alcohol [Y/n]: ");
        //String alcohol = c.readLine();
        input = scanner.next();
        input += scanner.nextLine();
        boolean alcohol = "Yy ".indexOf(input) >= 0;

        System.out.println(String.format("\n  You chose : %s %s %s \n",
                cheap, music, alcohol));
        Caracteristics car = new Caracteristics();
        car.setCheap(cheap);
        car.setMusic(music);
        car.setAlcohol(alcohol);

        System.out.println("\n --- Choose a Type of Search --- \n");
        System.out.print("  - Trip [Y/n]: ");
        //String trip = c.readLine();
        input = scanner.next();
        input += scanner.nextLine();
        boolean trip = "Yy ".indexOf(input) >= 0;
        System.out.print("  - Radius: ");
        //String trip = c.readLine();
        input = scanner.next();
        input += scanner.nextLine();
        int radius = Integer.parseInt(input);

        System.out.println(String.format("\n  You chose : %s %d\n",
                trip, radius));
        Preferences pref = new Preferences();
        pref.addCaracteristics(car);
        pref.setTrip(trip);
        pref.setRadius(radius);

        // Create user
        return new User(pseudo, pos, pref);
    }
    /**
     *  Method main.
     *
     * @param args Args of main function
     */
    public static void main(final String[] args) {

        String intro = "\n======================="
                + "\n| Welcome to Barathon |"
                + "\n======================= \n";
        System.out.println(intro);

        User user = cheapInterface();
        //To do: run Search
        user.generate();

        //To do: show result
        user.show();
    }

    private class CheapInterface {

        private Console c;
        private Scanner s;
        private ArrayList<String> commands;

        public Console getC() {
            return c;
        }

        public void setC(Console c) {
            this.c = c;
        }

        public Scanner getS() {
            return s;
        }

        public void setS(Scanner s) {
            this.s = s;
        }

        CheapInterface(){
            this.c = System.console();
            this.s  = new Scanner(System.in);
            this.commands = new ArrayList<String>();
            this.commands.add("NewUser");
            this.commands.add("ChangeUser");
            this.commands.add("DeleteUser");
            this.commands.add("NewCarac");
            this.commands.add("ChangeCarac");
            this.commands.add("DeleteCarac");
            this.commands.add("GetTrip");
            this.commands.add("GetBars");

        }

        public void ask_task(){
            String input = "";
            System.out.print("  What would you like to do? ");
            input = s.next();
            input += s.nextLine();
            String command  = input;
            if (command.equals("NewUser")){
                this.newUser();
            }
        }

        private User newUser() {
            String input = "";
            System.out.print("  --CREATING NEW USER-- ");
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
            return new User(pseudo, pos, pref);
        }

        public Preferences newPreferences(){
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
    }
}
