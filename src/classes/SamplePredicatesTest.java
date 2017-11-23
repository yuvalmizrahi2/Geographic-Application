package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class SamplePredicatesTest {

	@Test
	void testFilterSample() {
		ArrayList<Sample> s=new ArrayList<Sample>();
		Wifi w1=new Wifi("Robert1" , "3c:1e:04:03:7f:17", "2412", "-81");
		Wifi w2=new Wifi("DIRECT-35-HP DeskJet 3830 series", "98:e7:f4:c6:4b:37", "2437", "-87");
		Wifi w3=new Wifi("BezeqFree","6a:12:f5:f9:5e:71","2447","-85");
		ArrayList<Wifi> arraywifi=new ArrayList<Wifi>();
		arraywifi.add(w1);
		arraywifi.add(w2);
		arraywifi.add(w3);

		Sample s1=new Sample();
		s1.setArraywifi(arraywifi);
		WayPoint p=new WayPoint(32.0909486, 40.00, 56);
		s1.setWaypoint(p);
		s1.setDate(new Date(2017, 10, 27, 16, 12, 01));

		Sample s2=new Sample();
		s2.setArraywifi(arraywifi);
		WayPoint p2=new WayPoint(40.0909486, 34.8786141, 56);
		s1.setWaypoint(p2);
		s2.setDate(new Date(2017, 10, 27, 18, 12, 01));

		ArrayList<Sample> befor=new ArrayList<Sample>();
		befor.add(s1);
		befor.add(s2);
		ArrayList<Sample> after=new ArrayList<Sample>();
		after = SamplePredicates.filterSample(befor, SamplePredicates.isidequalto("yuval"));


		assertTrue(after.size()==0);
	}

}
