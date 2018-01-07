package intputoutputtest;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import inputoutput.CsvToSamples;
import sample.Sample;
import sample.WayPoint;


class CsvToSamplesTest {

	@Test
	void testconvertstringtosample() {
		Class<?> c = CsvToSamples.class;
		Object o;
		try
		{
			o = c.newInstance();
			Method m = c.getDeclaredMethod("convertstringtosample", new Class[]{String.class});
			m.setAccessible(true);
			Sample test = (Sample) m.invoke(o,"2017-10-27 16:16:37,PB2PRO,32.1676301,34.80987737,45.0,1,888Corp,0a:8d:cb:6e:71:be,5220,-87");
			boolean flag = test.getId().equals("PB2PRO")&& test.getWaypoint().equals(new WayPoint(32.1676301,34.80987737,45.0))
					&& test.getArraywifi().size() == 1;
			assertTrue(flag);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testreadfile()
	{
		ArrayList<Sample> samples = CsvToSamples.readfile("CsvToSamplesTest.csv");
		assertTrue(samples.size() == 1);
		}

}
