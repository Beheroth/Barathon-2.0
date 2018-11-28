import org.junit.*;


public class ComponentTest {

	@Test
	public void TestIsEqual() {

		Assert.assertTrue((new Drink("Drink", 10, 5.2, true)).isEqual((new Drink("Drink", 10, 5.2, true))));
		Assert.assertFalse((new Drink("Drink", 10, 5.2, true)).isEqual((new Drink("Beer", 10, 5.2, true))));
		Assert.assertTrue((new Drink("Drink", 10, 5.2, true)).isEqual((new Drink("Drink", 8, 5.2, true))));
		Assert.assertTrue((new Drink("Drink", 10, 5.2, true)).isEqual((new Drink("Drink", 10, 0.1, true))));
		Assert.assertTrue((new Drink("Drink", 10, 5.2, true)).isEqual((new Drink("Drink", 10, 5.2, false))));

		Assert.assertFalse((new Drink("Drink", 10, 5.2, true)).isEqual((new Food("Food", 10, true, true, true))));

		Assert.assertTrue((new Food("Food", 10, true, true, true)).isEqual((new Food("Food", 10, true, true, true))));
		Assert.assertFalse((new Food("Food", 10, true, true, true)).isEqual((new Food("Hamburger", 10, true, true, true))));
		Assert.assertTrue((new Food("Food", 10, true, true, true)).isEqual((new Food("Food", 12, true, true, true))));
		Assert.assertTrue((new Food("Food", 10, true, true, true)).isEqual((new Food("Food", 10, false, true, true))));
		Assert.assertTrue((new Food("Food", 10, true, true, true)).isEqual((new Food("Food", 10, true, false, true))));
		Assert.assertTrue((new Food("Food", 10, true, true, true)).isEqual((new Food("Food", 10, true, true, false))));
	}
}
