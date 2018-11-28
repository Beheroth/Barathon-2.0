/**
 * Class to represent the position.
 */
public class Position {
    /**
     * longitude.
     */
    private Double longitude;
    /**
     * latitude.
     */
    private Double latitude;

    /**
     * Method to generate a position.
     *
     * @param lo longitude.
     * @param la latitude.
     */
    public Position(final Double lo, final Double la) {
        this.longitude = lo;
        this.latitude = la;
    }

    /**
     * Method to get the longitude.
     *
     * @return a double representing the longitude.
     */
    public final Double getLongitude() {
        return this.longitude;
    }

    /**
     * Method to set the longitude.
     *
     * @param lo a double representing the longitude.
     */
    public final void setLongitude(final Double lo) {
        this.longitude  = lo;
    }

    /**
     * Method to get the latitude.
     *
     * @return a double representing the latitude.
     */
    public final Double getLatitude() {
        return this.latitude;
    }

    /**
     * Method to set the latitude.
     *
     * @param la a double representing the latitude.
     */
    public final void setLatitude(final Double la) {
        this.latitude = la;
    }

    /**
     * Method to check the equality.
     *
     * @param p a position.
     *
     * @return a boolean (true if equals).
     */
    public final boolean isEqual(final Position p) {
        try {
            int result = Double.compare(this.longitude, p.longitude);
            int result2 = Double.compare(this.latitude, p.latitude);
            return result == 0 && result2 == 0;
        } catch (Exception e) {
            System.out.println("Position Error: Double.compare exception");
            return false;
        }
    }
}
