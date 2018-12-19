import java.util.ArrayList;

/**
 * Class to find the bars.
 */
public class StrategyBars implements Strategy {
  /**
   *  Method solve.
   *
   * @param u user to search from.
   * @return list of places.
   */
    public final ArrayList<Place> solve(final User u) {
        Caracteristics c = u.getCurrentCaracteristics();
        ArrayList<Place> trip = new ArrayList<Place>();
        //trip = DBAccess.getNearbyPlaces(u);
        filter(trip, c);
        return trip;
    }

    /**
     * Method sort.
     *
     * @param trip List of places to filter.
     * @param userCaracteristics Caracteristics specified by the user.
     * @return List of places that match the userCaracteristics.
     */
    private void filter(ArrayList<Place> trip, Caracteristics userCaracteristics) {
        for (Place place : trip) {
            Caracteristics placeCaracteristics = place.getCaracteristics();
            if (!userCaracteristics.equals(placeCaracteristics)) {
                trip.remove(place);
            }
        }
    }
}
