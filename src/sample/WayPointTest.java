package sample;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class WayPointTest {

	@Test
	void testequals1() {
		WayPoint wayPoint1=new WayPoint(2.4,4.5,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.5,6.7)	;
		assertTrue(wayPoint1.equals(wayPoint2));
	}
	@Test
	void testequals2() {
		WayPoint wayPoint1=new WayPoint(2.4,4.6,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.600000001,6.7)	;
		assertTrue(!wayPoint1.equals(wayPoint2));
	}
	@Test
	void testtoString()
	{
		WayPoint wayPoint=new WayPoint(2.4,4.6,6.7);
		assertTrue(wayPoint.toString().equals("2.4,4.6,6.7"));
	}

}
