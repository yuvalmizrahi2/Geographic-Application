package classproject;


public class Main {
	public static void main(String[] args) {
		CsvFiles files = new CsvFiles("C:\\test");
		KmlFile kml = new KmlFile(files.getOutfilename()+".csv");

	}
}
