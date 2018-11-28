import java.util.ArrayList;

/**
 * Class to represent a Place.
 */
public class Place {
    /**
     * place's id (unique).
     */
    private int id;
    /**
     * place's name.
     */
    private String name;
    /**
     * place's address.
     */
    private Address address;
    /**
     * place's menu.
     */
    private Menu menu;
    /**
     * place's caracteristics.
     */
    private Caracteristics caracteristics;

    /**
     * Method to generate a place.
     *
     * @param i place's id.
     * @param n place's name.
     * @param a place's address.
     * @param m place's menu.
     * @param c place's caracteristics.
     */
    public Place(final int i, final String n, final Address a, final Menu m,
    final Caracteristics c) {
        this.id = i;
        this.name = n;
        this.address = a;
        this.menu = m;
        this.caracteristics = c;
    }

    /**
     * Method to get the place's id.
     *
     * @return an int representing the place's id.
     */
    public final int getId() {
        return this.id;
    }

    /**
     * Method to set the place's id.
     *
     * @param i an int representing the place's id.
     */
    public final void setId(final int i) {
        this.id = i;
    }

    /**
     * Method to get the place's name.
     *
     * @return a string representing the place's name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Method to set the place's name.
     *
     * @param n a string representing the place's name.
     */
    public final void setName(final String n) {
        this.name = n;
    }

    /**
     * Method to get the place's address.
     *
     * @return an Address representing the place's address.
     */
    public final Address getAddress() {
        return this.address;
    }

    /**
     * Method to set the place's address.
     *
     * @param a an address representing the place's address.
     */
    public final void setAddress(final Address a) {
        this.address = a;
    }

    /**
     * Method to get the place's menu.
     *
     * @return a Menu representing the place's menu.
     */
    public final Menu getMenu() {
      return this.menu;
    }

    /**
     * Method to set the place's menu.
     *
     * @param m a manu representing the place's menu.
     */
    public final void setMenu(final Menu m) {
        this.menu = m;
    }

    /**
    * Method to get the place's menu.
    *
    * @return a Menu representing the place's menu.
    */
    public final Caracteristics getCaracteristics() {
        return this.caracteristics;
    }

    /**
    * Method to set the place's carcateristics.
    *
    * @param c carcateristics representing the place's caracteristics.
    */
    public final void setCaracteristics(final Caracteristics c) {
        this.caracteristics = c;
    }

    /**
    * Method to get a place from a list of places.
    *
    * @param places an array list of places.
    * @param id an int representing a place's id.
    * @return a Place object.
    */
    public static Place findPlace(final ArrayList<Place> places,
    final int id) {
        for (Place p: places) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
