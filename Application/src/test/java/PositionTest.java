import org.junit.*;

public class PositionTest {

	@Test
	public void TestIsEqual() {

		Position p = new Position(42.6, 63.5);
		Position q = new Position(21.3, 78.1124);

		Assert.assertFalse(p.isEqual(q));
		Assert.assertFalse(q.isEqual(p));

		Assert.assertTrue(p.isEqual(new Position(42.6, 63.5)));
		Assert.assertFalse(p.isEqual(new Position(42.6, 0.0)));
		Assert.assertFalse(p.isEqual(new Position(0.0, 63.5)));
		Assert.assertFalse(p.isEqual(new Position(0.0, 0.0)));

		Assert.assertTrue(q.isEqual(new Position(21.3, 78.1124)));
		Assert.assertFalse(q.isEqual(new Position(21.3, 0.0)));
		Assert.assertFalse(q.isEqual(new Position(0.0, 78.1124)));
		Assert.assertFalse(q.isEqual(new Position(0.0, 0.0)));
	}
}
