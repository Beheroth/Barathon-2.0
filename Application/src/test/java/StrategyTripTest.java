import org.junit.*;

public class StrategyTripTest {

/*
 	@Before
    public void InitClasses() {
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
		pl = new Place(1, "Bar quelconque", a, m, c);
		List<Place> ar = new ArrayList<>(pl);
		d = new Drink("Boisson soft", 2, 0, true);
	}*/

	@Test
	public void TestFilter() {
        //InitClasses();
		List<Place> ar = new ArrayList<>(pl);
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		pl = new Place(1, "Bar quelconque", a, m, c);
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
        filter(ar, c);

        Assert.assertTrue(ar.isEqual(new arraylist<>(pl)));
        Assert.assertFalse(ar.isEqual(new arraylist<>()));
	}
}
