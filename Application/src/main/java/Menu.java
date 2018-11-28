import java.util.ArrayList;

/**
* This class represents the menu of a place.
*/
public class Menu {
    /**
    * @param foods List of menu's food.
    */
    private ArrayList<Food> foods;
    /**
    * @param drinks List of menu's drink.
    */
    private ArrayList<Drink> drinks;
    /**
    * @param meanDrinkPrice mean price of drinks.
    */
    private int meanDrinkPrice;
    /**
    * @param meanFoodPrice mean price of food.
    */
    private int meanFoodPrice;

    /**
    * Constructor.
    */
    public Menu() {
        this.foods = new ArrayList<Food>();
        this.drinks = new ArrayList<Drink>();
    }

    /**
    * Method to get the food's mean price.
    *
    * @return int mean price.
    */
    public final int getMeanFoodPrice() {
        return this.meanFoodPrice;
    }

    /**
    * Method to get the drink's mean price.
    *
    * @return int mean price.
    */
    public final int getMeanDrinkPrice() {
        return this.meanDrinkPrice;
    }

    /**
    * This method gets all the foods from the menu.
    *
    * @return ArrayList<Food> Food from the menu.
    */
    public final ArrayList<Food> getFoods() {
        return this.foods;
    }

    /**
    * This method gets all the drinks from the menu.
    *
    * @return ArrayList<Drink> Drink from the menu.
    */
    public final ArrayList<Drink> getDrinks() {
        return this.drinks;
    }

    /**
    * This method adds a food to the menu.
    *
    * @param f Food to be added.
    */
    public final void addFood(final Food f) {
        this.foods.add(f);
        this.setMeanFoodPrice();
    }

    /**
    * This method removes a food from the menu.
    *
    * @param f Food to be removed.
    */
    public final void removeFood(final Food f) {
        this.foods.remove(f);
        this.setMeanFoodPrice();
    }

    /**
    * This method adds a drink to the menu.
    *
    * @param d Drink to be added.
    */
    public final void addDrink(final Drink d) {
        this.drinks.add(d);
        this.setMeanDrinkPrice();
    }

    /**
    * This method removes a drink from the menu.
    *
    * @param d Drink to be removed.
    */
    public final void removeDrink(final Drink d) {
        this.drinks.remove(d);
        this.setMeanDrinkPrice();
    }

    /**
    * Compute the price of the foods.
    */
    public final void setMeanFoodPrice() {
        int sum = 0;
        // Add the price of each element in the list
        for (Food f : this.foods) {
            sum += f.getPrice();
        }
        if (this.foods.size() > 0) {
            this.meanFoodPrice = sum / this.foods.size();
        } else {
            this.meanFoodPrice = 0;
        }
    }

    /**
    * Compute the price of the drinks.
    */
    public final void setMeanDrinkPrice() {
        int sum = 0;
        // Add the price of each element in the list
        for (Drink d : this.drinks) {
            sum += d.getPrice();
        }
        if (this.drinks.size() > 0) {
            this.meanDrinkPrice = sum / this.drinks.size();
        } else {
            this.meanDrinkPrice = 0;
        }
    }

    /**
    * Compute the mean price of a list of components - drinks or foods.
    *
    * @param components The full list of the components.
    *
    * @return int mean price of the components.
    */
    private int computeMeanPrice(
    final ArrayList<Component> components) {
        int sum = 0;
        // Add the price of each element in the list
        for (Component c : components) {
            sum += c.getPrice();
        }
        return sum / components.size();
    }
}
