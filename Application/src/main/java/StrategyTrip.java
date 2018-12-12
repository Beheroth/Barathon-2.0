// import
import java.util.ArrayList;

/**
 * Class to find the trip of bars.
 */
public class StrategyTrip implements Strategy {
  /**
   *  Method solve.
   *
   * @param u user to search from.
   * @return list of ordered places.
   */
    public final ArrayList<Place> solve(final User u) {
        Caracteristics c = u.getCurrentCaracteristics();
        Position pos = u.getPosition();
        ArrayList<Place> trip = new ArrayList<Place>();
        //trip = DBAccess.getNearbyPlaces(p);
        filter(trip, c);
        circularSort(trip, pos);

        // Take parameters position and carcateristics which match the places.
        return trip;
    }

    private void filter(ArrayList<Place> trip, Caracteristics userCaracteristics){
        for (Place place : trip) {
            Caracteristics placeCaracteristics = place.getCaracteristics();
            if(!userCaracteristics.equals(placeCaracteristics)){
                trip.remove(place);
            }
        }
    }

    private void circularSort(ArrayList<Place> trip, Position pos){
        Place firstPlace = getNearest(trip, pos);
    }

    private Place getNearest(ArrayList<Place> places, Position pos){
        Place result = null;
        try{
            result = places.get(0);   //What if the list is empty
        }
        catch (Exception e){
            System.out.println("Couldn't get nearest place");
        }

        for(Place place : places){
            if(pos.getDistanceFrom(place.getAddress().getPosition()) < pos.getDistanceFrom(result.getAddress().getPosition())){
                result = place;
            }
        }
        return result;
    }
}
