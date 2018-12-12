import org.junit.*;

public class StrategyTripTest {

	public Position p = new Position(16.38, 48.25);
	public Adress a = new Address("Rue du barathon", "10", p );
	public Menu m = new Menu();
	public Caracteristics c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
	public Place pl = new Place(1, "Bar quelconque", a, m, c);
	public List<Place> ar = new ArrayList<>(pl);
	public Drink d = new Drink("Boisson soft", 2, 0, true);

	@Test
	public void TestFilter() {        
		filter(ar, c);
        Assert.assertTrue(ar.isEqual(new arraylist<>(pl)));
        Assert.assertFalse(ar.isEqual(new arraylist<>()));
	}
}
