package classproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * A class that receives a folder and collects the samples from each file
 * and indexes them into a single file
 * @author יובל מזרחי
 *
 */
public class CsvFiles
{
	private ArrayList<Sample> files;
	private String path;
	private String outfilename;
	/**
	 * A constructor that receives the parameter path
	 * @param path
	 */
	public CsvFiles(String path) {
		this.path = path;
		this.files = IOCsvFile.readfile(this.path);
		this.outfilename = getcurrenttime();
		IOCsvFile.writefile(this.outfilename+".csv" , this.files);
	}
	/**
	 * function that return the value of the arraylist<sample>
	 * @return arraylist<sample>
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
	/**
	 * function that return the value of the outfilename
	 * @return
	 */
	public String getOutfilename() {
		return outfilename;
	}
	/**
	 * function that get name and change the path outfilename
	 * @param outfilename
	 */
	public void setOutfilename(String outfilename) {
		this.outfilename = outfilename;
	}
	/**
	 * A function that returns the current time
	 * @return time
	 */
	private static String getcurrenttime()

	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}
}
