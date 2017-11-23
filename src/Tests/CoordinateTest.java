package Tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.Coordinate;

class CoordinateTest {

	@Test
	void testequals1() {
		Coordinate c1=new Coordinate(15.2);
		Coordinate c2=new Coordinate(15.2);
		assertTrue(c1.equals(c2));
	}
	
	@Test
	void testequals2() {
		Coordinate c1=new Coordinate(15.2);
		Coordinate c2=new Coordinate(15.200001);
		assertTrue(!c1.equals(c2));
	}

}
