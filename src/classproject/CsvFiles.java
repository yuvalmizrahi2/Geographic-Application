package classproject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/**
 * A class that receives a folder and collects the samples from each file
 * and indexes them into a single file
 * @author יובל מזרחי
 *
 */
public class CsvFiles implements IOFile
{
	private ArrayList<Sample> files;
	private String path;
	private String outfilename;
	/**
	 * A constructor that receives the parameter path
	 * @param path
	 */
	public CsvFiles(String path) {
		this.files = new ArrayList<>();
		this.path = path;
		readfile();
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
	 * Shell function for the function readfile(string path) 
	 */
	@Override
	public void readfile() 
	{
		String namefile = getcurrenttime();
		readfile(this.path);
		Collections.sort(this.files);
		writefile(namefile+".csv");
		this.outfilename = namefile;

	}
	/**
	 * A function that accepts the entire sample collection and writes it to a csv file
	 */
	@Override
	public void writefile(String filename) {
		FileWriter fw = null;
		PrintWriter outs = null;
		try {
			fw = new FileWriter("output/"+filename);
			outs = new PrintWriter(fw);
			outs.print("Date,ID,Lat,Lon,Alt,#WiFi networks");
			for(int i = 1 ; i <= 10 ; i++)
			{
				outs.print(",SSID"+i+",MAC"+i+",Frequncy"+i+",Signal"+i);
			}	
			outs.println();
			for(int i = 0; i < this.files.size() ; i++)
			{
				if(this.files.get(i).getArraywifi().size() != 0)
				{
					outs.print(this.files.get(i).toString());
					outs.print(Math.min(10, this.files.get(i).getArraywifi().size()));
					for(int j = 0; j < Math.min(10, this.files.get(i).getArraywifi().size()) ; j++ )
					{
						outs.print(this.files.get(i).getArraywifi().get(j).toString());
					}
					outs.println();
				}
			}
			outs.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Exception thrown  :" + e);
		}
	}
	/**
	 * A function that accepts a path and passes a file file recursively
	 * and reads each file and takes the samples
	 * @param path
	 */
	private void readfile(String path) 
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0 ; i < listOfFiles.length ; i++)
		{
			if(listOfFiles[i].isFile())
			{
				this.files.addAll(SortedSample.readfile(listOfFiles[i].getPath()));
			}
			else if(listOfFiles[i].isDirectory())
			{
				readfile(listOfFiles[i].getPath());
			}
		}

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
