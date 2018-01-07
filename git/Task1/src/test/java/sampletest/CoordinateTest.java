package sampletest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sample.Coordinate;

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
	@Test
	void testtoString()
	{
		Coordinate c=new Coordinate(15.2);
		assertTrue(c.toString().equals("15.2"));
	}

}
