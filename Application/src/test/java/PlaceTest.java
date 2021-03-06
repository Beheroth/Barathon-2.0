import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PlaceTest {

	public Position p;
	public Address a ;
	public Menu m ;
	public Caracteristics c ;		
	public Place pl ;
	public ArrayList<Place> ar;
	public Drink d;

	@Before
	public void InitClasses() {
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
		pl = new Place(1, "Bar quelconque", a, m, c);
		ar = new ArrayList<Place>();
		ar.add(pl);
		d = new Drink("Boisson soft", 2, 0, true);
	}


	@Test
	public void TestsetCaracteristics() {
		c.set(true, false, false, false, false, false, false, false);
		Assert.assertEquals(true, c.getCheap());
	}
/*
	@Test
	public void TestFindPlace() {
		Assert.assertEquals(pl, findPlace(ar, 1));
	}
*/
}
