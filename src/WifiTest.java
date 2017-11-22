import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WifiTest {



	@Test
	void testconvertChannelToFrequency1() {
		Wifi w1=new Wifi("123", "dre6", "2412", "-90");

		assertTrue(w1.getFrequncy().equals(w1.convertChannelToFrequency(1)));
	}
	@Test
	void testconvertChannelToFrequenc2() {
		Wifi w1=new Wifi("123", "dre6", "2412", "-90");

		assertTrue(!w1.getFrequncy().equals(w1.convertChannelToFrequency(2)));
	}
	@Test
	void testconvertChannelToFrequency3() {
		Wifi w1=new Wifi("123", "dre6", "2462", "-90");

		assertTrue(w1.getFrequncy().equals(w1.convertChannelToFrequency(11)));
	}
	@Test
	void compareTo1() {
		Wifi w1=new Wifi("123", "dre6", "2462", "-90");
		Wifi w2=new Wifi("123", "dre6", "2462", "-90");

		assertTrue(w1.compareTo(w2)==0);
	}

	@Test
	void compareTo2() {
		Wifi w1=new Wifi("123", "dre6", "2462", "-90");
		Wifi w2=new Wifi("123", "dre6", "2462", "-90");

		assertTrue(!(w1.compareTo(w2)!=0));
	}
	@Test
	void compareTo3() {
		Wifi w1=new Wifi("ariel uni", "dre6", "2462", "-90");
		Wifi w2=new Wifi("ariel uni", "dre6", "2462", "0");

		assertTrue(w1.compareTo(w2)>0);
	}
	@Test
	void compareTo4() {
		Wifi w1=new Wifi("ariel uni", "dre6", "2462", "5");
		Wifi w2=new Wifi("ariel uni", "dre6", "2462", "0");

		assertTrue(!(w1.compareTo(w2)<0));
	}


}
