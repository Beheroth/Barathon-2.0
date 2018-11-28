import org.junit.*;


public class DrinkTest {

	@Test
	public void TestGetSoft() {

		Assert.assertFalse((new Drink("Drink", 10, 9.7, false)).getSoft());
		Assert.assertFalse((new Drink("Drink", 10, 9.7, true)).getSoft());
		Assert.assertFalse((new Drink("Drink", 85, 40.0, false)).getSoft());
		Assert.assertFalse((new Drink("Drink", 85, 40.0, true)).getSoft());
		Assert.assertFalse((new Drink("Drink", 10, 121.3789, false)).getSoft());
		Assert.assertFalse((new Drink("Drink", 10, 121.3789, true)).getSoft());
		Assert.assertTrue((new Drink("Drink", 0, 0.0, false)).getSoft());
		Assert.assertTrue((new Drink("Drink", 0, 0.0, true)).getSoft());
		Assert.assertFalse((new Drink("Drink", 10, -5.0, false)).getSoft());
		Assert.assertFalse((new Drink("Drink", 10, -5.0, true)).getSoft());

	}
}
