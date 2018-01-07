package sample;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SortedSampleTest {

	@Test
	void testChangeforamt1() {
		
		assertTrue("2017-12-03 08:37:10".equals(SortedSample.changeforamt("2017-12-03 08:37:10")));
	}
	@Test
	void testChangeforamt2() {
		
		assertTrue("2017-12-05 11:33:00".equals(SortedSample.changeforamt("12/05/17 11:33 AM")));
	}
	@Test
	void testChangeforamt3() {
		assertTrue("2017-12-05 11:33:00".equals(SortedSample.changeforamt("05/12/2017 11:33")));
	}

	@Test
	void testReadfile() {
		ArrayList<Sample> test = SortedSample.readfile("filetest\\other\\WigleWifi_20171201110804.csv");
		assertTrue(test.size() == 31);
	}
	@Test
	void getsampletest()
	{
		String str = "c0:ac:54:f5:b4:c9,HOTBOX-D88A,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:42:54,1,-92,32.17224246105308,34.8144489381276,7.777438959165592,4,WIFI";
		String[] test = str.split(",");
		Class<?> c = SortedSample.class;
		Object o;
		try {
			o = c.newInstance();
			Method m = c.getDeclaredMethod("getsample", new Class[]{String[].class,String.class});
			m.setAccessible(true);
			Sample sample = (Sample) m.invoke(o, test,"dreamlte");
			assertTrue(sample.getId().equals("dreamlte"));
			assertTrue(sample.getWaypoint().equals(new WayPoint(32.17224246105308,34.8144489381276,7.777438959165592)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
