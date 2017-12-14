package classproject;

import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {
//		CsvFiles files = new CsvFiles("C:\\test");
//			KmlFile kml = new KmlFile(files.getOutfilename()+".csv");
		ArrayList<Sample> database = new ArrayList<>();
		database.addAll(CsvToSamples.readfile("Algo1_file_test.csv"));
//		ArrayList<Sample> person = new ArrayList<>();
//		person.addAll(CsvToSamples.readfile("_comb_no_gps_ts1.csv"));
//		PersonLocation.findpersonlocation(database, person);
		IOCsvFile.writefile("yuval.csv", MacLocation.findmaclocation(database));
	}
}
