import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlaceTest {

	@Before

	public void InitClasses() {
		p = new Position(16.38, 48.25);
		a = new Address("Rue du barathon", "10", p );
		m = new Menu();
		c = new Caracteristics();
		c.set(false, false, false, false, false, false, false, false);
		pl = new Place(1, "Bar quelconque", a, m, c);
		d = new Drink("Boisson soft", 2, 0, true);
	}


	@Test

	public void TestUpdateCaracteristics() {
		c.updateCaracteristics(true, false, false, false, false, false, false, false);
		Assert.assertEquals(1, c.getCheap());
	}





	@Test
	public void TestFindPlace() {


	}

}