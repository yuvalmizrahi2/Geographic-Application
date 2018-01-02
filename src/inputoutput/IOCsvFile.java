package inputoutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import sample.Sample;
import sample.SortedSample;
/**
 * A class that contain all the input or output function on csv file
 * @author יובל מזרחי
 *
 */
public class IOCsvFile {
	private final static int srongestwif = 10;
	/**
	 * A function shell for readfile
	 * @param path
	 */
	public static ArrayList<Sample> readfile(String path) 
	{
		ArrayList<Sample> files = new ArrayList<>();
		readfile(path, files);
		return files;
	}
	/**
	 * A function that accepts a path and passes a file file recursively
	 * and reads each file and takes the samples 
	 * @param path
	 * @param files
	 */
	private static void readfile(String path , ArrayList<Sample> files) 
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0 ; i < listOfFiles.length ; i++)
		{
			if(listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith("csv"))
			{
				files.addAll(SortedSample.readfile(listOfFiles[i].getPath()));
			}
			else if(listOfFiles[i].isDirectory())
			{
				readfile(listOfFiles[i].getPath() , files);
			}
		}
	}
	/**
	 * A function that accepts the entire sample collection and writes it to a csv file
	 */
	public static void writefile(String filename , ArrayList<Sample> files , String dir) {
		Collections.sort(files);
		FileWriter fw = null;
		PrintWriter outs = null;
		try {
			fw = new FileWriter(dir+"/"+filename);
			outs = new PrintWriter(fw);
			outs.print("Date,ID,Lat,Lon,Alt,#WiFi networks");
			for(int i = 1 ; i <= 10 ; i++)
			{
				outs.print(",SSID"+i+",MAC"+i+",Frequncy"+i+",Signal"+i);
			}	
			outs.println();
			for(int i = 0; i < files.size() ; i++)
			{
				if(files.get(i).getArraywifi().size() != 0)
				{
					outs.print(files.get(i).toString());
					outs.print(Math.min(srongestwif, files.get(i).getArraywifi().size()));
					for(int j = 0; j < Math.min(srongestwif, files.get(i).getArraywifi().size()) ; j++ )
					{
						outs.print(files.get(i).getArraywifi().get(j).toString());
					}
					outs.println();
				}
			}
			outs.close();
			fw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error in writing file", "Worng", JOptionPane.ERROR_MESSAGE);

		}
	}

}
