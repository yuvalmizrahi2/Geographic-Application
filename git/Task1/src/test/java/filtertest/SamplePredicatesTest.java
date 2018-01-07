package filtertest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import filter.SamplePredicates;
import inputoutput.CsvToSamples;
import sample.Sample;

class SamplePredicatesTest {

	@Test
	void testReplicateMac() {
		ArrayList<Sample> test = CsvToSamples.readfile("CsvToSamplesTest.csv");
		test = SamplePredicates.replicateMac(test);
		assertTrue(test.size() == 1);
	}

}
