import java.io.Console;
import java.util.ArrayList;

/**
 * Class to represent an user.
 */
public class User {
    /**
     * user's pseudo.
     */
    private String pseudo;
    /**
     * list of bars.
     */
    private ArrayList<Place> bars;
    /**
     * list of bars.
     */
    private ArrayList<Place> trips;
    /**
     * user's position.
     */
    private Position position;
    /**
     * user's preferences.
     */
    private Preferences preferences;
    /**
     * user's choice.
     */
    private int choice;

    /**
     * Method to generate an user.
     *
     * @param ps user's pseudo.
     * @param po user's position.
     * @param pr user's preferences.
     */
    public User(final String ps, final Position po, final Preferences pr) {
        this.pseudo = ps;
        this.bars = new ArrayList<Place>();
        this.trips = new ArrayList<Place>();
        this.position = po;
        this.preferences = pr;
        this.choice = 0;
    }

    /**
     * Method to get the user's pseudo.
     *
     * @return a string representing the user's pseudo.
     */
    public final String getPseudo() {
        return this.pseudo;
    }

    /**
     * Method to set the user's pseudo.
     *
     * @param p a string representing the user's pseudo.
     */
    public final void setPseudo(final String p) {
        this.pseudo = p;
    }

    /**
     * Method to get a list of bars.
     *
     * @return a list representing the bars to visit.
     */
    public final ArrayList<Place> getBars() {
        return this.bars;
    }

    /**
     * Method to set a list of bars.
     *
     * @param b list representing the bars to visit.
     */
    public final void setBars(final ArrayList<Place> b) {
        this.bars = b;
        return;
    }

    /**
     * Method to add a bar to the list of bars.
     *
     * @param b place representing the bar to add.
     */
    public final void addBar(final Place b) {
        this.bars.add(b);
        return;
    }

    /**final
     * Method to remove a bar of the list.
     *
     * @param b place representing the bar to remove.
     */
    public final void removeBar(final Place b) {
        if (this.bars.contains(b)) {
            this.bars.remove(b);
        } else {
            System.out.println("Place not deleted (not found)");
        }
    }

    /**
     * Method to get a list of bars.
     *
     * @return a list representing the bars to visit.
     */
    public final ArrayList<Place> getTrips() {
        return this.trips;
    }

    /**
     * Method to set a list of bars.
     *
     * @param t list representing the bars to visit.
     */
    public final void setTrips(final ArrayList<Place> t) {
        this.trips = t;
    }

    /**
     * Method to add a bar to the list of bars.
     *
     * @param t place representing the bar to add.
     */
    public final void addTrip(final ArrayList<Place> t) {
        this.trips.addAll(t);
    }

    /**
     * Method to remove a bar of the list.
     *
     * @param t place representing the bar to remove.
     */
    public final void removeTrip(final ArrayList<Place> t) {
        if (this.trips.contains(t)) {
            this.trips.remove(t);
        } else {
            System.out.println("Trip not deleted (not found)");
        }
    }

    /**
     *  Method to get the user's Position.
     *
     * @return a position representing the user's position.
     */
    public final Position getPosition() {
        return this.position;
    }

    /**
     * Method to set the user's position.
     *
     * @param p a position representing the user's position.
     */
    public final void setPosition(final Position p) {
        this.position = p;
    }

    /**
     * Method to get the user's preferences.
     *
     * @return a preferences representing the user's preferences.
     */
    public final Preferences getPreferences() {
        return this.preferences;
    }

    /**
     * Method to set the user's preferences.
     *
     * @param pr a preferences representing the user's preferences.
     */
    public final void setPreferences(final Preferences pr) {
        this.preferences = pr;
    }

    /**
     *  Method to set the user's choice.
     *
     * @param c a int representing the user's choice.
     */
    public final void setChoice(final int c) {
        this.choice = c;
    }

    /**
     *  Method to get the user's choice.
     *
     * @return a int representing the user's choice.
     */
    public final int getChoice() {
        return this.choice;
    }

    /**
     *  Method to clear the lists bars and trips.
     */
    private void clearList() {
        this.bars = new ArrayList<Place>();
        this.trips = new ArrayList<Place>();
    }

    /**
     *  Method to generate the lists via the search algorithm.
     */
    public final void generate() {
        //to implement
        this.clearList();
        if (this.preferences.getTrip()) {
            StrategyTrip st = new StrategyTrip();
            this.trips = st.solve(this.position,
            this.preferences.getCaracteristics().get(0));
        } else if (!this.preferences.getTrip()) {
            StrategyBars sb = new StrategyBars();
            this.bars = sb.solve(this.position,
            this.preferences.getCaracteristics().get(0));
        }
    }

    /**
     *  Method to show the lists.
     */
    public final void show() {
        if (this.preferences.getTrip()) {
            System.out.println(this.trips);
        } else if (!this.preferences.getTrip()) {
            System.out.println(this.bars);
        }
    }

    /**
     *  Method to choose the caracteristic.
     *
     * @return a int representing the user's choice.
     */
    public final int chooseCaracterictics() {
        Console console = System.console();

        String msg = String.format("Choose Caracteristic [0-%01d]",
        this.preferences.getNbCaracteristic());
        System.out.println(msg);
        int input = Integer.parseInt(console.readLine());
        //TO REMOVE
        this.choice = input;
        return input;
    }

    /**
     *  Method to find the corresponding user in the db.
     *
     * @param users a list of user representing all the users of the db.
     * @param pseudo a string representing the user's pseudo.
     * @return null
     */
    public static User findUser(final ArrayList<User> users,
    final String pseudo) {
        for (User u: users) {
            if (u.getPseudo().equals(pseudo)) {
                return u;
            }
        }
        return null;
    }
}
