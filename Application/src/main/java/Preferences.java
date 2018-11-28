import java.util.ArrayList;

/**
 *  Class representing the user's preferences.
 */
public class Preferences {
    /**
     * @param caracteristics caracteristics's list.
     */
    private ArrayList<Caracteristics> caracteristics;
    /**
     * @param radius max radius of deplacement.
     */
    private int radius;
    /**
     * @param trip boolean true for a trip.
     */
    private boolean trip;

    /**
     * When the user position is unknown, the coordinates are (666,666).
    **/
    private static final int UNKNOWN_RADIUS = 1000;

    /**
     * Constructor.
     */
    public Preferences() {
        this.caracteristics = new ArrayList<Caracteristics>();
        this.radius = UNKNOWN_RADIUS;
        this.trip = false;
    }

    /**
     * Method to get the caracteristics.
     *
     * @return List of caracteristics.
     */
    public final ArrayList<Caracteristics> getCaracteristics() {
      return this.caracteristics;
    }

    /**
     * Method to get the number of caracteristics.
     *
     * @return int representing the nulber of caracteristics.
     */
    public final int getNbCaracteristic() {
        return this.caracteristics.size();
    }

    /**
     * Method to add a caracteristic.
     *
     * @param c caracteristic to add.
     */
    public final void addCaracteristics(final Caracteristics c) {
        this.caracteristics.add(c);
    }

    /**
     * Method to remove a caracteristic.
     *
     * @param c caracteristic to remove.
     */
    public final void removeCaracteristics(final Caracteristics c) {
      if (this.caracteristics.contains(c)) {
        this.caracteristics.remove(c);
      } else {
        System.out.println("Caracteristics not deleted (not found)");
      }
    }

    /**
     * Method to get the radius.
     *
     * @return int representing the radius.
     */
    public final int getRadius() {
        return radius;
    }

    /**
     * Method to set radius.
     *
     * @param r representing the radius.
     */
    public final void setRadius(final int r) {
        if (r > 0) {
            this.radius = r;
        } else {
            this.radius = 0;
        }
    }

    /**
     * Method to get trip.
     *
     * @return boolean true if trip.
     */
    public final boolean getTrip() {
        return this.trip;
    }

    /**
     * Method to set trip.
     *
     * @param t boolean trip.
     */
    public final void setTrip(final boolean t) {
        this.trip = t;
    }
}
