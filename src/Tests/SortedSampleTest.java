package Tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.SortedSample;

class SortedSampleTest {

	@Test
	void testChangeforamt() {
		String date1="27/10/2017 16:12:01"; 
		date1 = SortedSample.changeforamt(date1);
		String date2="2017-10-27 16:12:01";
		assertTrue(date1.equals(date2));
	}
	
}

