import org.junit.*;


public class FoodTest {

	@Test
	public void TestGetVegan() {

	Assert.assertTrue((new Food("Food", 10, true, true, true)).getVegan());
        Assert.assertTrue((new Food("Food", 10, true, true, false)).getVegan());
        Assert.assertTrue((new Food("Food", 10, true, false, true)).getVegan());
        Assert.assertTrue((new Food("Food", 10, true, false, false)).getVegan());
        Assert.assertFalse((new Food("Food", 10, false, true, true)).getVegan());
        Assert.assertFalse((new Food("Food", 10, false, true, false)).getVegan());
        Assert.assertFalse((new Food("Food", 10, false, false, true)).getVegan());
        Assert.assertFalse((new Food("Food", 10, false, false, false)).getVegan());

	}

    	@Test
	public void TestGetHalal() {

        Assert.assertTrue((new Food("Food", 10, true, true, true)).getHalal());
        Assert.assertTrue((new Food("Food", 10, true, true, false)).getHalal());
        Assert.assertFalse((new Food("Food", 10, true, false, true)).getHalal());
        Assert.assertFalse((new Food("Food", 10, true, false, false)).getHalal());
        Assert.assertTrue((new Food("Food", 10, false, true, true)).getHalal());
        Assert.assertTrue((new Food("Food", 10, false, true, false)).getHalal());
        Assert.assertFalse((new Food("Food", 10, false, false, true)).getHalal());
        Assert.assertFalse((new Food("Food", 10, false, false, false)).getHalal());
		 

	}

    	@Test
	public void TestGetVegetarian() {

	Assert.assertTrue((new Food("Food", 10, true, true, true)).getVegetarian());
        Assert.assertFalse((new Food("Food", 10, true, true, false)).getVegetarian());
        Assert.assertTrue((new Food("Food", 10, true, false, true)).getVegetarian());
        Assert.assertFalse((new Food("Food", 10, true, false, false)).getVegetarian());
        Assert.assertTrue((new Food("Food", 10, false, true, true)).getVegetarian());
        Assert.assertFalse((new Food("Food", 10, false, true, false)).getVegetarian());
        Assert.assertTrue((new Food("Food", 10, false, false, true)).getVegetarian());
        Assert.assertFalse((new Food("Food", 10, false, false, false)).getVegetarian());
        
	}
}
