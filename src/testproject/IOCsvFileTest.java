package testproject;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import classproject.CsvToSamples;
import classproject.IOCsvFile;
import classproject.Sample;

class IOCsvFileTest {

	@Test
	void testReadfile() {
		ArrayList<Sample> test = IOCsvFile.readfile("filetest");
		assertTrue(test.size() == 3821);

	}

	@Test
	void testWritefile() {
		ArrayList<Sample> test = IOCsvFile.readfile("filetest");
		IOCsvFile.writefile("IOCsvFileTest.csv", test);
		test = CsvToSamples.readfile("IOCsvFileTest.csv");
		assertTrue(test.size() == 3607);
	}

}
