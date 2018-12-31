import java.util.ArrayList;

/**
 * Class to implements a research strategy based on bars preferences.
 */
public class StrategyBars implements Strategy {
  /**
   *  Method solve.
   *
   * @param u user to search from.
   * @return list of places.
   */
    public final ArrayList<Place> solve(final User u) {
        int num= 10;
        Caracteristics c = u.getCurrentCaracteristics();
        ArrayList<Place> trip = new ArrayList<Place>();
        trip = DBAccess.getNearbyPlaces(u, num);
        filter(trip, c);
        return trip;
    }

    /**
     * Method sort.
     *
     * @param trip List of places to filter.
     * @param userCaracteristics Caracteristics specified by the user.
     */
    private void filter(final ArrayList<Place> trip, final Caracteristics userCaracteristics) {
        for (Place place : trip) {
            Caracteristics placeCaracteristics = place.getCaracteristics();
            if (!userCaracteristics.isEqual(placeCaracteristics)) {
                trip.remove(place);
            }
        }
    }
}
