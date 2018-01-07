package algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import inputoutput.CsvToSamples;
import sample.Sample;
import sample.WayPoint;


class FindLocationTest {

	@Test
	void testlocationbysingal() {
		Class<?> c1 = FindLocation.class;
		Class<?> c2 = CsvToSamples.class;
		Object o;
		try
		{
			o = c2.newInstance();
			Method m = c2.getDeclaredMethod("convertstringtosample", new Class[]{String.class});
			m.setAccessible(true);
			ArrayList<Sample> test = new ArrayList<>();
			test.add((Sample) m.invoke(o,"2017-10-27 16:13:51,OnePlus3T,32.103,35.208,650.0,1,Howmanyistwo,74:da:38:50:77:f2,2417,-30.0"));
			test.add((Sample) m.invoke(o,"2017-10-27 16:14:37,PB2PRO,32.105,35.205,660.0,1,Howmanyistwo,74:da:38:50:77:f2,2417,-80.0"));
			test.add((Sample) m.invoke(o,"2017-10-27 16:15:52,OnePlus3T,32.103,35.307,680.0,1,Howmanyistwo,74:da:38:50:77:f2,2417,-90.0"));
			o = c1.newInstance();
			m = c1.getDeclaredMethod("locationbysingal", new Class[]{ArrayList.class});
			m.setAccessible(true);
			assertTrue(m.invoke(o,test).equals(new WayPoint(32.10322468793343,35.2164507628294,653.7864077669904)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
