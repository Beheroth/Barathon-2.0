import org.junit.*;

public class PositionTest {

    public void InitClasses() {
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
		pl = new Place(1, "Bar quelconque", a, m, c);
		list<Place> ar = new arraylist<>(pl);
		d = new Drink("Boisson soft", 2, 0, true);
	}

	@Test
	public void TestFilter() {
        InitClasses();

        filter(ar, c);

        Assert.assertTrue(ar.isEqual(new arraylist<>(pl)));
        Assert.assertFalse(ar.isEqual(new arraylist<>)));
		
	}
}
