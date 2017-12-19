package other;

import java.util.ArrayList;

import algorithm.MacLocation;
import inputoutput.CsvToSamples;
import inputoutput.IOCsvFile;
import kindfile.CsvFiles;
import sample.Sample;

public class Main {
	public static void main(String[] args) {
		CsvFiles files = new CsvFiles("C:\\test\\boaz");
//			KmlFile kml = new KmlFile(files.getOutfilename()+".csv");
		ArrayList<Sample> database = new ArrayList<>();
		database.addAll(CsvToSamples.readfile(files.getOutfilename()+".csv"));
//		ArrayList<Sample> person = new ArrayList<>();
//		person.addAll(CsvToSamples.readfile("_comb_no_gps_ts1.csv"));
//		PersonLocation.findpersonlocation(database, person);
		IOCsvFile.writefile("yuval.csv", MacLocation.findmaclocation(database));
	}
}
