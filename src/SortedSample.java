
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/**
 * A class containing a set of samples that each sample contains the location, id, and wifi array
 * @author יובל מזרחי
 *
 */
public class SortedSample 
{
	/**
	 * A function that receives a full date in a particular format
	 * and returns in the format of yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return date in format yyyy-MM-dd HH:mm:ss
	 */
	private static String changeforamt(String date)
	{
		date = date.replaceAll("/", "-");
		if(date.split(" ")[0].split("-")[0].length() == 2)
		{
			String str = date.split(" ")[0].split("-")[2]+"-"+date.split(" ")[0].split("-")[1]+"-"+date.split(" ")[0].split("-")[0]+" "+date.split(" ")[1];
			date = str;
		}
		if(date.split(" ")[1].split(":").length == 2)
			date = date+":00";
		return date;
	}
	/**
	 * A function that receives a set of string and id
	 * and returns a sample with the data obtained from the array and the id
	 * @param temp
	 * @param id
	 * @return a sample
	 */
	private static Sample getsample(String[] temp , String id)
	{
		Sample sample = new Sample();
		WayPoint point = new WayPoint(Double.parseDouble(temp[6]), Double.parseDouble(temp[7]), Double.parseDouble(temp[8]));
		sample.setWaypoint(point);
		sample.setId(id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		temp[3] = changeforamt(temp[3]);
		Date date = null;
		try {
			date = df.parse(temp[3]);
		} catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		sample.setDate(date);
		return sample;
	}
	/**
	 * A function that receives a set of string
	 * and returns a wifi with the data obtained from the array
	 * @param temp
	 * @return a wifi
	 */
	private static Wifi getwifi(String[] temp)
	{
		Wifi wifi = new Wifi();
		wifi.setMac(temp[0]);
		wifi.setSsid(temp[1]);
		int fre = Integer.parseInt(temp[4]);
		wifi.setFrequncy(wifi.convertChannelToFrequency(fre));
		wifi.setSingal(temp[5]);
		return wifi;
	}
	/**
	 * A function that receives name of a file reads it
	 * and returns an array of samples according to the data received from the file
	 * @param filename
	 * @return an arraylist of sample
	 */
	public static ArrayList<Sample> readfile(String filename)
	{
		ArrayList<Sample> arrayofwifi = new ArrayList<>();
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String str = br.readLine();
			String[] temp = str.split(",");
			String id = temp[4].split("=")[1];
			str = br.readLine();
			str = br.readLine();
			temp = str .split(",");
			arrayofwifi.add(getsample(str.split(",") , id));
			int k = 0;
			while(str != null)
			{
				temp = str .split(",");
				Sample tempsample = getsample(temp,id);
				if(tempsample.equals(arrayofwifi.get(k)))
				{
					if(temp[10].equals("WIFI") && !temp[8].equals("0"))
					{
						arrayofwifi.get(k).getArraywifi().add(getwifi(temp));
					}
				}
				else
				{
					Collections.sort(arrayofwifi.get(k).getArraywifi());
					k++;
					arrayofwifi.add(tempsample);
					if(temp[10].equals("WIFI") && !temp[8].equals("0"))
					{
						arrayofwifi.get(k).getArraywifi().add(getwifi(temp));
					}

				}
				str = br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception thrown  :" + e);
		}
		return arrayofwifi;
	}
}