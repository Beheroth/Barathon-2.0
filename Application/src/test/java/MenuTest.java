import org.junit.*;

public class MenuTest {

	public Drink d1, d2, d3, d4;
	public Food f1, f2, f3, f4;


	@Before
	public void InitClasses() {

		d1 = new Drink("Drink", 100, 0, false);
		d2 = new Drink("Drink", 200, 0, false);
		d3 = new Drink("Drink", 300, 0, false);
		d4 = new Drink("Drink", 400, 0, false);

		f1 = new Food("Food", 100, false, false, false);
		f2 = new Food("Food", 200, false, false, false);
		f3 = new Food("Food", 300, false, false, false);
		f4 = new Food("Food", 400, false, false, false);
	}

	@Test
	public void TestAddDrink() {
		Menu m = new Menu();

		Assert.assertEquals(0, m.getDrinks().size());
		m.addDrink(d1);
		Assert.assertEquals(1, m.getDrinks().size());
		m.addDrink(d2);
		Assert.assertEquals(2, m.getDrinks().size());
		m.addDrink(d3);
		Assert.assertEquals(3, m.getDrinks().size());
	}

	@Test
	public void TestRemoveDrink() {
		Menu m = new Menu();

		m.addDrink(d1);
		m.addDrink(d2);

		Assert.assertEquals(2, m.getDrinks().size());
		m.removeDrink(d1);
		Assert.assertEquals(1, m.getDrinks().size());
		m.removeDrink(d1);
		Assert.assertEquals(1, m.getDrinks().size());
		m.removeDrink(d2);
		Assert.assertEquals(0, m.getDrinks().size());
	}

	@Test
	public void TestAddFood() {
		Menu m = new Menu();

		Assert.assertEquals(0, m.getFoods().size());
		m.addFood(f1);
		Assert.assertEquals(1, m.getFoods().size());
		m.addFood(f2);
		Assert.assertEquals(2, m.getFoods().size());
		m.addFood(f3);
		Assert.assertEquals(3, m.getFoods().size());
	}

	@Test
	public void TestRemoveFood() {
		Menu m = new Menu();

		m.addFood(f1);
		m.addFood(f2);

		Assert.assertEquals(2, m.getFoods().size());
		m.removeFood(f1);
		Assert.assertEquals(1, m.getFoods().size());
		m.removeFood(f1);
		Assert.assertEquals(1, m.getFoods().size());
		m.removeFood(f2);
		Assert.assertEquals(0, m.getFoods().size());
	}

	@Test
	public void TestGetMeanDrinkPrice() {
		Menu m = new Menu();

		Assert.assertEquals(0, m.getMeanDrinkPrice());
		m.addDrink(d1);
		Assert.assertEquals(100, m.getMeanDrinkPrice());
		m.addDrink(d2);
		Assert.assertEquals(150, m.getMeanDrinkPrice());
		m.addDrink(d3);
		Assert.assertEquals(200, m.getMeanDrinkPrice());
		m.addDrink(d4);
		Assert.assertEquals(250, m.getMeanDrinkPrice());
		m.removeDrink(d4);
		Assert.assertEquals(200, m.getMeanDrinkPrice());
	}

	@Test
	public void TestGetMeanFoodPrice() {
		Menu m = new Menu();

		Assert.assertEquals(0, m.getMeanFoodPrice());
		m.addFood(f1);
		Assert.assertEquals(100, m.getMeanFoodPrice());
		m.addFood(f2);
		Assert.assertEquals(150, m.getMeanFoodPrice());
		m.addFood(f3);
		Assert.assertEquals(200, m.getMeanFoodPrice());
		m.addFood(f4);
		Assert.assertEquals(250, m.getMeanFoodPrice());
		m.removeFood(f4);
		Assert.assertEquals(200, m.getMeanFoodPrice());
	}
}
