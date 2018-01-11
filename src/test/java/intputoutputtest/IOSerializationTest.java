package intputoutputtest;

import static org.junit.Assert.*;

import org.junit.Test;

import filter.Filter;
import filter.IdFilter;
import filter.NonOperator;
import filter.Operator;
import inputoutput.IOSerialization;

public class IOSerializationTest {

	@Test
	public void testWriteAndReadObject() {
		Filter f1 = new IdFilter("yuval");
		NonOperator o2 = new NonOperator(f1);
		IOSerialization.WriteObject("Non-TestIOSerialization.txt", o2, "output");
		NonOperator o3 = (NonOperator) IOSerialization.ReadObject("output\\Non-TestIOSerialization.txt", "Non");
		assertTrue(o2.getFilter().equals(o3.getFilter()));
	}

	

}
