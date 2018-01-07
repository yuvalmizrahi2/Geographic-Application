package wraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algorithm.MacLocation;
import algorithm.PersonLocation;
import database.DataBase;
import inputoutput.CsvToSamples;
import sample.Sample;
import sample.WayPoint;

/**
 * A class that contion al the functions that connected to the 
 * first and the second algorithm
 * @author יובל מזרחי
 *
 */
public class Algorithm {
	/**
	 * A shell function that use in the first algorithm
	 * @param database
	 * @param mac
	 * @return
	 */
	private static WayPoint Algo1(DataBase database , String mac) {
		WayPoint[] point = new WayPoint[1];

		WayPoint temp = MacLocation.findmaclocation(mac, database.getMap());
		point[0] = new WayPoint(temp);
		return point[0];
	}
	/**
	 * A function that show in google static map the result
	 * of the first algorithm
	 * @param database
	 * @param mac
	 * @return
	 */
	public static ImageIcon GetMapLocation(DataBase database , String mac)
	{
		WayPoint point = new WayPoint(Algo1(database, mac));
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
	}
	/**
	 * A shell function for convert a string to sample
	 * @param str
	 * @param database
	 * @return
	 */
	private static WayPoint StringToSample (String str , DataBase database) {
		Sample sample = CsvToSamples.convertstringtosample(str);
		ArrayList<String> mac = new ArrayList<>();
		ArrayList<Double> signal = new ArrayList<>();
		for(int i = 0 ; i < sample.getArraywifi().size() ; i++)
		{
			mac.add(sample.getArraywifi().get(i).getMac());
			signal.add(sample.getArraywifi().get(i).getSingal());
		}
		return PersonLocation.findpersonlocation(mac, signal, database.getMap());
	}
	/**
	 * A shell function that use in the second algorithm
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	private static WayPoint ThreeMac (ArrayList<String> mac , ArrayList<Double> signal , DataBase database) {
		return PersonLocation.findpersonlocation(mac, signal, database.getMap());
	}
	/**
	 * A function that use in the second algorithm
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	public static ImageIcon Algo2(String string , DataBase database)
	{
		WayPoint point = StringToSample(string, database);
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
	}
	/**
	 * A function that return the result of the second algorithm
	 * on google static map
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	public static ImageIcon Algo2(ArrayList<String> mac , ArrayList<Double> signal , DataBase database)
	{
		WayPoint point = ThreeMac(mac, signal, database);
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
	}
}
