/**
* Class to represent the food.
*/
public class Food extends Component {
    /**
    * vegan field.
    */
    private boolean vegan;
    /**
    * halal field.
    */
    private boolean halal;
    /**
    * vegetarian field.
    */
    private boolean vegetarian;

    /**
    * Method to generate the food.
    *
    * @param n The name of the food.
    * @param p The price of the food.
    * @param vn If the food is vegan : true.
    * @param h If the food is halal : true.
    * @param vg If the food is vegetarian : true.
    */
    public Food(final String n, final int p, final boolean vn,
    final boolean h, final boolean vg) {
        super(n, p);
        this.vegan = vn;
        this.halal = h;
        this.vegetarian = vg;
    }

    /**
    * Method to get the vegan field.
    *
    * @return a boolean vegan field.
    */
    public final boolean getVegan() {
        return this.vegan;
    }

    /**
    * Method to get the halal field.
    *
    * @return a boolean halal field.
    */
    public final boolean getHalal() {
        return this.halal;
    }

    /**
    * Method to get the vegetarian field.
    *
    * @return a boolean vegetarian field.
    */
    public final boolean getVegetarian() {
        return this.vegetarian;
    }

    /**
    * Method to set the vegan field.
    *
    * @param vn a boolean representing the vegan field.
    */
    public final void setVegan(final boolean vn) {
        this.vegan = vn;
    }

    /**
    * Method to set the halal field.
    *
    * @param h a boolean representing the halal field.
    */
    public final void setHalal(final boolean h) {
        this.halal = h;
    }

    /**
    * Method to set the vegetarian field.
    *
    * @param vg a boolean representing the vegetarian field.
    */
    public final void setVegetarian(final boolean vg) {
        this.vegetarian = vg;
    }
}
