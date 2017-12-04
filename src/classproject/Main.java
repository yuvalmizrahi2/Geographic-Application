package classproject;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		//		CsvFiles files = new CsvFiles("C:\\test");
		//				KmlFile kml = new KmlFile(files.getOutfilename()+".csv");

		ArrayList<String> mac = new ArrayList<>();
		mac.add("18:d6:c7:fb:1c:0e");
		mac.add("c8:60:00:b1:d4:50");
		mac.add("e4:6f:13:73:0a:c2");
		ArrayList<Double> singal = new ArrayList<>();
		singal.add(-80.0);
		singal.add(-80.0);
		singal.add(-80.0);
		System.out.println(PersonLocation.findpersonlocation(mac, singal, "20171123110845.csv"));
	}
}
