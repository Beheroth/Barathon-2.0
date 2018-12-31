import java.util.ArrayList;

/**
 * Class to find the trip of bars.
 */
public class StrategyTrip implements Strategy {
    /**
     * Method solve.
     *
     * @param u user to search from.
     * @return list of places ordered following the nearest neighbour algorithm.
     */
    public final ArrayList<Place> solve(final User u) {
        num= 10;
        Position pos = u.getPosition();
        ArrayList<Place> trip = new ArrayList<Place>();
        trip = DBAccess.getNearbyPlaces(u, num);
        sort(trip, pos);
        return trip;
    }

    /**
     * Method sort.
     *
     * @param trip List of places to sort.
     * @param pos Position of the starting point.
     * @return List of places sorted following the nearest neighbour algorithm, starting from 'pos'.
     */
    private ArrayList<Place> sort(final ArrayList<Place> trip, final Position pos) {
        ArrayList<Place> sortedtrip = new ArrayList<Place>();
        while (!trip.isEmpty()) {
            int nearest = 0;
            for (int i = 1; i < trip.size(); i++) {
                Position uncheckedpos = trip.get(i).getAddress().getPosition();
                Position nearestpos = trip.get(nearest).getAddress().getPosition();
                if (pos.getDistanceFrom(uncheckedpos) < pos.getDistanceFrom(nearestpos)) {
                    nearest = i;
                }
            }
            sortedtrip.add(trip.get(nearest));
            trip.remove(nearest);
        }
        return sortedtrip;
    }
}
