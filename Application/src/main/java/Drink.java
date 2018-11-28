/**
* This class represents the drinks to be added to the menu of a place.
*/
public class Drink extends Component {
    /**
     * @param alcohol degrees of alcohol.
     */
     private double alcohol;
     /**
     * @param sparkling boolean representing if the drink is sparkling
     */
    private boolean sparkling;

    /**
    * Constructor.
    *
    * @param n The name of the drink.
    * @param p The price of the drink.
    * @param a The alcochol % of the drink.
    * @param s If the drink is sparkling : true.
    */
    public Drink(final String n, final  int p, final double a,
    final boolean s) {
        super(n, p);
        this.alcohol = a;
        this.sparkling = s;
    }

    /**
    * Method to get the drink's alcohol percent.
    *
    * @return double percent of alcohol.
    */
    public final double getAlcohol() {
        return this.alcohol;
    }

    /**
    * Method to get the drink's sparkliung.
    *
    * @return boolean true if sparkling.
    */
    public final boolean getSparkling() {
        return this.sparkling;
    }

    /**
    * Method to set the percent of alcohol.
    *
    * @param a percent of alcohol.
    */
    public final void setAlcohol(final double a) {
        this.alcohol = a;
    }

    /**
    * Method to set the sparkling.
    *
    * @param s boolean of sparkling.
    */
    public final void setSparkling(final boolean s) {
        this.sparkling = s;
    }

    /**
    * This method checks if the drink is alcohol-free.
    *
    * @return boolean TRUE if there is no alcohol.
    */
    public final boolean getSoft() {
        // A drink is soft if it contains no alcohol
        try {
            double zero = 0.0;
            int result = Double.compare(this.alcohol, zero);
            if (result == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(
            "Alcohol Comparison Error: Double.compare exception");
            return false;
        }
    }
}
