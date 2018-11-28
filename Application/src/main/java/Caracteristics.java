/**
 * Class to represent the caracteristics.
 */
 public class Caracteristics {
   /**
    * carcateristic cheap.
    */
    private boolean cheap;
    /**
     * carcateristic music.
     */
    private boolean music;
    /**
     * carcateristic famousPlace.
     */
    private boolean famousPlace;
    /**
     * carcateristic food.
     */
    private boolean food;
    /**
     * carcateristic vegetarian.
     */
    private boolean vegetarian;
    /**
     * carcateristic halal.
     */
    private boolean halal;
    /**
     * carcateristic vegan.
     */
    private boolean vegan;
    /**
     * carcateristic alcohol.
     */
    private boolean alcohol;

    /**
     * Method to generate the caracteristics.
     *
     */
    public Caracteristics() {
        this.cheap = false;
        this.music = false;
        this.famousPlace = false;
        this.food = false;
        this.vegetarian = false;
        this.halal = false;
        this.vegan = false;
        this.alcohol = false;
    }

    /**
     * Method to get the cheap field.
     *
     * @return a boolean representing the cheap field.
     */
    public final boolean getCheap() {
        return this.cheap;
    }

    /**
     * Method to get the music field.
     *
     * @return a boolean representing the music field.
     */
    public final boolean getMusic() {
        return this.music;
    }

    /**
     * Method to get the famousPlace field.
     *
     * @return a boolean representing the famousPlace field.
     */
    public final boolean getFamousPlace() {
        return this.famousPlace;
    }

    /**
     * Method to get the food field.
     *
     * @return a boolean representing the food field.
     */
    public final boolean getFood() {
        return this.food;
    }

    /**
     * Method to get the vegetarian field.
     *
     * @return a boolean representing the vegetarian field.
     */
    public final boolean getVegetarian() {
        return this.vegetarian;
    }

    /**
     * Method to get the halal field.
     *
     * @return a boolean representing the halal field.
     */
    public final boolean getHalal() {
        return this.halal;
    }

    /**
     * Method to get the vegan field.
     *
     * @return a boolean representing the vegan field.
     */
    public final boolean getVegan() {
        return this.vegan;
    }

    /**
     * Method to get the alcohol field.
     *
     * @return a boolean representing the alcohol field.
     */
    public final boolean getAlcohol() {
        return this.alcohol;
    }

    /**
     * Method to set the cheap field.
     *
     * @param c a boolean representing the cheap field.
     */
    public final void setCheap(final boolean c) {
        this.cheap = c;
    }

    /**
     * Method to set the music field.
     *
     * @param m a boolean representing the music field.
     */
    public final void setMusic(final boolean m) {
        this.music = m;
    }

    /**
     * Method to set the famousPlace field.
     *
     * @param fp a boolean representing the famousPlace field.
     */
    public final void setFamousPlace(final boolean fp) {
        this.famousPlace = fp;
    }

    /**
     * Method to set the food field.
     *
     * @param f a boolean representing the food field.
     */
    public final void setFood(final boolean f) {
        this.food = f;
    }

    /**
     * Method to set the vegetarian field.
     *
     * @param vg a boolean representing the vegetarian field.
     */
    public final void setVegetarian(final boolean vg) {
        this.vegetarian = vg;
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
     * Method to set the vegan field.
     *
     * @param vn a boolean representing the cheap field.
     */
    public final void setVegan(final boolean vn) {
        this.vegan = vn;
    }

    /**
     * Method to set the alcohol field.
     *
     * @param a a boolean representing the alcohol field.
     */
    public final void setAlcohol(final boolean a) {
        this.alcohol = a;
    }

    /**
     * Method to set the all fields.
     *
     * @param c a boolean representing the cheap field.
     * @param m a boolean representing the music field.
     * @param fp a boolean representing the famousPlace field.
     * @param f a boolean representing the food field.
     * @param vg a boolean representing the vegetarian field.
     * @param h a boolean representing the halal field.
     * @param vn a boolean representing the vegan field.
     * @param a a boolean representing the alcohol field.
     */
    public final void set(final boolean c, final boolean m, final boolean fp,
    final boolean f, final boolean vg, final boolean h, final boolean vn,
    final boolean a) {
        this.cheap = c;
        this.music = m;
        this.famousPlace = fp;
        this.food = f;
        this.vegetarian = vg;
        this.halal = h;
        this.vegan = vn;
        this.alcohol = a;
    }
}
