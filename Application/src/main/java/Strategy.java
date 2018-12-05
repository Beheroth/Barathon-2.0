import java.util.ArrayList;

/**
 *  Interface for the strategy pattern.
 */
interface Strategy {
    /**
     *  Method solve.
     *
     * @param u user to search from.
     * @return list of places.
     */
    ArrayList<Place> solve(User u);
}
