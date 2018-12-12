import org.junit.*;

public class StrategyTripTest {

	public Position p;
	public Address a ;
	public Menu m ;
	public Caracteristics c ;		
	public Place pl ;
	public ArrayList<Place> ar;
	public Drink d;

	@before
	public void InitClasses() {
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
		pl = new Place(1, "Bar quelconque", a, m, c);
		ar = new ArrayList<>(pl);
		d = new Drink("Boisson soft", 2, 0, true);
	}

	@Test
	public void TestFilter() {        
		filter(ar, c);
        Assert.assertTrue(ar.isEqual(new ArrayList<>(pl)));
        Assert.assertFalse(ar.isEqual(new ArrayList<>()));
	}
}
