package testproject;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import classproject.AlgoMath;
import classproject.Coordinate;

class AlgoMathTest {

	@Test
	void testweight() {
		double temp = AlgoMath.weight(1);
		assertTrue(temp == 1);
	}
	@Test
	void testcoordinateweight()
	{
		Coordinate coor = new Coordinate(1);
		double temp = AlgoMath.coordinateweight(coor, 1);
		assertTrue(temp == 1);
	}
	@Test
	void testgetdiff1()  
	{
		Class<?> c = AlgoMath.class;
		Object o;
		try
		{
			o = c.newInstance();
			Method m = c.getDeclaredMethod("getdiff", new Class[]{double.class,double.class});
			m.setAccessible(true);
			assertTrue(m.invoke(o, -40,-60).equals(23.0));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testgetdiff2()  
	{
		Class<?> c = AlgoMath.class;
		Object o;
		try
		{
			o = c.newInstance();
			Field f = c.getDeclaredField("diffnosig");
			f.setAccessible(true);
			Method m = c.getDeclaredMethod("getdiff", new Class[]{double.class,double.class});
			m.setAccessible(true);
			assertTrue(m.invoke(o, -40,-120).equals(f.get(c)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testweightdiff()
	{
		Class<?> c = AlgoMath.class;
		Object o;
		try
		{
			o = c.newInstance();
			Method m = c.getDeclaredMethod("weightdiff", new Class[]{double.class,double.class});
			m.setAccessible(true);
			assertTrue(m.invoke(o, -1,0).equals(5743.491774985175));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
