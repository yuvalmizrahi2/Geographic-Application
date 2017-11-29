package classproject;

import java.util.ArrayList;

/**
 * A class that creates a filtered kml file
 * @author יובל מזרחי
 *
 */
public class KmlFile  {
	private ArrayList<Sample> files;
	private String path;
	/**
	 * A constructor that receives the parameter path
	 * @param path
	 */
	public KmlFile(String path) {
		this.path = path;
		this.files = CsvToSamples.readfile(this.path);
		WriteKml.writefile(path.replaceFirst(".csv", ".kml"),this.files);
	}
	/**
	 * function that return the value of the arraylsit<sample>
	 * @return arraylsit<sample>
	 */
	public ArrayList<Sample> getFiles() {
		return files;
	}
	/**
	 * function that get arraylist<sample> and change the arraylist<sample> value
	 * @param files
	 */
	public void setFiles(ArrayList<Sample> files) {
		this.files = files;
	}
	/**
	 * function that return the value of the path
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * function that get path and change the path value
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
