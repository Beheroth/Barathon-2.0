/**
 *  This class represents the components to be added to a menu.
 *  This is the super class of Food and Drink.
 */
public abstract class Component {
    /**
     * @param name Component's name
     */
    private String name;
    /**
     * @param price Component's price
     */
    private int price;

    /**
     * Constructor.
     *
     * @param n The name of the component.
     * @param p The price of the component.
     */
    public Component(final String n, final int p) {
        this.name = n;
        this.price = p;
    }

    /**
     * This method checks if 2 Components are equals.
     *
     * @param c Component.
     * @return boolean TRUE if the 2 Components are equals.
     */
    public final boolean isEqual(final Component c) {
        //Need further implementation
        return this.name == c.getName();
    }

    /**
     * Method to get the component's name.
     *
     * @return string representing the component's name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Method to get the component's price.
     *
     * @return int representing the component's price.price
     */
    public final int getPrice() {
       return this.price;
   }

    /**
     * Method to set the component's name.
     *
     * @param n string representing the component's name.
     */
    public final void setName(final String n) {
        this.name = n;
    }

    /**
     * Method to set the component's price.
     *
     * @param p int representing the component's price.
     */
    public final void setPrice(final int p) {
        this.price = p;
    }
}
