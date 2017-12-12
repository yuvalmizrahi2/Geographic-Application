package classproject;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		CsvFiles files = new CsvFiles("C:\\test\\other2");
		//	KmlFile kml = new KmlFile(files.getOutfilename()+".csv");
		ArrayList<Sample> samples = new ArrayList<>();
		samples.addAll(CsvToSamples.readfile(files.getOutfilename()+".csv"));
		ArrayList<Sample> person = new ArrayList<>();
		person.addAll(CsvToSamples.readfile("_comb_no_gps_ts2_.csv"));
		PersonLocation.findpersonlocation(samples, person);
		int i = 0;
		for (Sample sample : person) {
			System.out.print(i+")"+sample.toString());
			System.out.print(sample.getArraywifi().size());
			for (Wifi wifi : sample.getArraywifi()) {
				System.out.print(wifi.toString());
			}
			System.out.println();
			i++;
		}
		IOCsvFile.writefile("yuval.csv", person);
//		ArrayList<Sample> mac = MacLocation.findmaclocation(samples);
//		for (Sample sample : mac) {
//			System.out.print(sample.toString());
//			System.out.print(sample.getArraywifi().size());
//			for (Wifi wifi : sample.getArraywifi()) {
//				System.out.print(wifi.toString());
//			}
//			System.out.println();
//		}
	}
}
