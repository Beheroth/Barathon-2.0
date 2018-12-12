// import
import java.util.ArrayList;

/**
 * Class to find the trip of bars.
 */
public class StrategyTrip implements Strategy {
    /**
     * Method solve.
     *
     * @param u user to search from.
     * @return list of ordered places.
     */
    public final ArrayList<Place> solve(final User u) {
        Caracteristics c = u.getCurrentCaracteristics();
        Position pos = u.getPosition();
        ArrayList<Place> trip = new ArrayList<Place>();
        //trip = DBAccess.getNearbyPlaces(u);
        filter(trip, c);
        sort(trip, pos);

        // Take parameters position and carcateristics which match the places.
        return trip;
    }

    private void filter(ArrayList<Place> trip, Caracteristics userCaracteristics) {
        for (Place place : trip) {
            Caracteristics placeCaracteristics = place.getCaracteristics();
            if (!userCaracteristics.equals(placeCaracteristics)) {
                trip.remove(place);
            }
        }
    }

    private void sort(ArrayList<Place> trip, Position pos) {
        ArrayList<Place> sortedtrip = new ArrayList<Place>();

    }
}
