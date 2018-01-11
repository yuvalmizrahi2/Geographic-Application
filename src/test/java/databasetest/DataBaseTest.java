package databasetest;
import database.DataBase;
import sample.Sample;
import sample.WayPoint;
import sample.Wifi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class DataBaseTest {

	@Test
	public void DataBasetest() {
		DataBase test = new DataBase();
		assertTrue(test.getSetsample() != null
				&& test.getMap() != null);
	}
	@Test
	public void Addtest()
	{
		DataBase test = new DataBase();
		Sample s = new Sample();
		s.setId("yuval");
		s.setWaypoint(new WayPoint());
		s.setDate(new Date());
		ArrayList<Wifi> arr = new ArrayList<>();
		Wifi w = new Wifi("Ariel_University", "1c:b9:c4:16:f4:2c", "2512", -81);
		arr.add(w);
		s.setArraywifi(arr);
		test.Add(s);
		assertTrue(test.getSetsample().size() == 1 &&
				test.getMap().size() == 1);
	}
	@Test
	public void Cleartest()
	{
		DataBase test = new DataBase();
		Sample s = new Sample();
		s.setId("yuval");
		s.setWaypoint(new WayPoint());
		s.setDate(new Date());
		ArrayList<Wifi> arr = new ArrayList<>();
		Wifi w = new Wifi("Ariel_University", "1c:b9:c4:16:f4:2c", "2512", -81);
		arr.add(w);
		s.setArraywifi(arr);
		test.Add(s);
		test.Clear();
		assertTrue(test.getSetsample().size() == 0 &&
				test.getMap().size() == 0);
	}

}
