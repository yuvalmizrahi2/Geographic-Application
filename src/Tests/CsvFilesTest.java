package Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import classes.CsvFiles;

class CsvFilesTest {

	
		
	@Test
	void testreadfile1() {
		CsvFiles c=new CsvFiles("C:\\test\\file\\check"); ///check small file with filter
		c.readfile();
		assertTrue(c.getFiles().size()==1);

	}

}
