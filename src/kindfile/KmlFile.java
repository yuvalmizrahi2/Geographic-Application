package kindfile;

import inputoutput.CsvToSamples;
import inputoutput.WriteKml;

/**
 * A class that creates a filtered kml file
 * @author יובל מזרחי
 *
 */
public class KmlFile extends File  {
	/**
	 * A constructor that receives the parameter path
	 * @param path
	 */
	public KmlFile(String path) {
		this.files = CsvToSamples.readfile(path);
		WriteKml.writefile(path.replaceFirst(".csv", ".kml"),this.files);
	}
}
