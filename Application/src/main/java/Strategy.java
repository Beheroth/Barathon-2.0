import java.util.ArrayList;

/**
 *  Interface for the strategy pattern.
 */
interface Strategy {
    /**
     *  Method solve.
     *
     * @param p position of the user.
     * @param c carcateristics of the user.
     * @return list of places.
     */
    ArrayList<Place> solve(Position p, Caracteristics c);
}
