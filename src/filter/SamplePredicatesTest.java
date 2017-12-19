package filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import inputoutput.CsvToSamples;
import sample.Sample;
import sample.WayPoint;

class SamplePredicatesTest {

	@Test
	void testIsidequalto() {
		ArrayList<Sample> test = CsvToSamples.readfile("IOCsvFileTest.csv");
		test = SamplePredicates.filterSample(test, SamplePredicates.isidequalto("yuval"));
		assertTrue(test.size() == 0);
	}

	@Test
	void testIsintheradius() {
		ArrayList<Sample> test = CsvToSamples.readfile("IOCsvFileTest.csv");
		WayPoint point = new WayPoint();
		test = SamplePredicates.filterSample(test, SamplePredicates.isintheradius(point, 0.0));
		assertTrue(test.size() == 0);
	}

	@Test
	void testIsinthetime() {
		ArrayList<Sample> test = CsvToSamples.readfile("IOCsvFileTest.csv");
		Date time = new Date();
		test = SamplePredicates.filterSample(test, SamplePredicates.isinthetime(time, time));
		assertTrue(test.size() == 0);
	}

	@Test
	void testReplicateMac() {
		ArrayList<Sample> test = CsvToSamples.readfile("CsvToSamplesTest.csv");
		test = SamplePredicates.replicateMac(test);
		assertTrue(test.size() == 1);
	}

}
