import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class WayPointTest {

	 
	@Test
	void testequals1() {
		WayPoint wayPoint1=new WayPoint(2.4,4.5,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.5,6.7)	;
		assertTrue(wayPoint1.equals(wayPoint2));
	}
	@Test
	void testequals2() {
		WayPoint wayPoint1=new WayPoint(2.4,4.6,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.5,6.7)	;
		assertTrue(!wayPoint1.equals(wayPoint2));
	}
	@Test
	void testequals3() {
		WayPoint wayPoint1=new WayPoint(2.4,4.6,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.60,6.7)	;
		assertTrue(wayPoint1.equals(wayPoint2));
	}
	@Test
	void testequals4() {
		WayPoint wayPoint1=new WayPoint(2.4,4.6,6.7)	;
		WayPoint wayPoint2=new WayPoint(2.4,4.600000001,6.7)	;
		assertTrue(!wayPoint1.equals(wayPoint2));
	}

}


