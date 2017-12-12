package classproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * A class that convert a row to sample
 * @author יובל מזרחי
 *
 */
public class CsvToSamples {
	/**
	 * A function that accepts a string line and turns it into a sample
	 * @param row
	 * @return a sample
	 */
	private static Sample convertstringtosample(String row)
	{
		String[] temp = row.split(",");
		Sample sample = new Sample();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		temp[0] = SortedSample.changeforamt(temp[0]);
		try {
			date = df.parse(temp[0]);
		} catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		temp[0] = temp[0].replaceAll(" ", "T");
		temp[0] = temp[0].replaceAll("/", ":");
		temp[0] = temp[0] + "Z";
		sample.setDate(date);
		sample.setId(temp[1]);
		if(temp[2].equals("?"))
			sample.setWaypoint(new WayPoint());
		else
			sample.setWaypoint(new WayPoint(Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), Double.parseDouble(temp[4])));
		ArrayList<Wifi> wifis = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(temp[5]) ; i++ )
		{
			Wifi wifi = new Wifi();
			wifi.setSsid(temp[6 + i*4]);
			wifi.setMac(temp[7 + i*4]);
			wifi.setFrequncy(temp[8 + i*4]);
			wifi.setSingal(Double.parseDouble(temp[9 + 4*i]));
			wifis.add(wifi);
		}
		sample.setArraywifi(wifis);
		return sample;
	}
	/**
	 * A function that reads a file according to the path received from the constructor
	 * and transforms the file into a set of samples
	 */
	public static ArrayList<Sample> readfile(String path) {
		FileReader fr;
		BufferedReader br;
		ArrayList<Sample> files = new ArrayList<>();
		try {
			fr = new FileReader("output/"+path);
			br = new BufferedReader(fr);
			String str = br.readLine();
			if(str.contains("ID"))
				str = br.readLine();
			while(str != null)
			{
				files.add(convertstringtosample(str));
				str = br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception thrown  :" + e);
		}
		return files;

	}
}
