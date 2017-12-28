package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;


import algorithm.MacLocation;
import algorithm.PersonLocation;
import database.DataBase;
import filter.AndOperator;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.OrOperator;
import filter.SamplePredicates;
import inputoutput.CsvToSamples;
import inputoutput.IOCsvFile;
import inputoutput.IOSerialization;
import inputoutput.WriteKml;
import sample.Sample;
import sample.WayPoint;
/**
 * A class that contaion all the functions that connection between the gui and the other project's codes
 * @author יובל מזרחי
 *
 */
public class Wraper {
	/**
	 * A shell function for saving filter
	 * @param dir
	 * @param namefile
	 * @param filter
	 * @param start
	 */
	public static void SaveFilter(JLabel dir , JTextField namefile,Operator filter,String start)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				IOSerialization.WriteObject(start+"-"+namefile.getText()+".txt", filter, dir.getText());

			}
		}).start();
	}
	/**
	 * A shell function for saving csv file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveCsvFile(JLabel dir , JTextField namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					IOCsvFile.writefile(namefile.getText()+".csv", new ArrayList<>(database.getSetsample()),dir.getText());
				}				
			}
		}).start();

	}
	/**
	 * A shell function for saving kml file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveKmlFile(JLabel dir , JTextField namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					WriteKml.writefile(namefile.getText()+".kml", new ArrayList<>(database.getSetsample()), dir.getText());
				}

			}
		}).start();
	}
	/**
	 * A shell function for filter the database by a filter
	 * @param filter
	 * @param database
	 */
	public static void FilterDataBase(Operator[] filter , DataBase database)
	{
		if(filter[0] instanceof NonOperator)
		{
			filter[0] = (NonOperator)filter[0];
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (database) {
						database.setSetsample(SamplePredicates.filterSampleWithOperator(database.getSetsample(), filter[0])); 
					}
				}
			}).start();
		}
		else if(filter[0] instanceof AndOperator)
		{
			filter[0] = (AndOperator)filter[0];
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (database) {
						database.setSetsample(SamplePredicates.filterSampleWithOperator(database.getSetsample(), filter[0])); 
					}
				}
			}).start();
		}
		else if(filter[0] instanceof OrOperator)
		{
			filter[0] = (OrOperator)filter[0];
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (database) {
						database.setSetsample(SamplePredicates.filterSampleWithOperator(database.getSetsample(), filter[0])); 
					}
				}
			}).start();
		}
		else
		{
			filter[0] = (FilterNotFilterOperator)filter[0];
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (database) {
						database.setSetsample(SamplePredicates.filterSampleWithOperator(database.getSetsample(), filter[0])); 
					}
				}
			}).start();
		}
	}
	/**
	 * A shell function for adding csv file
	 * @param pathfile
	 * @param database
	 * @param temp
	 */
	public static void AddCsv(JLabel pathfile , DataBase database , DataBase temp) {
		String str = pathfile.getText();
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					database.AddCollection(CsvToSamples.readfile(str));
					temp.AddDataBase(database);
				}

			}
		}).start();
		pathfile.setText("No Selection");
	}
	/**
	 * A shell function for adding folder that contain wigle files
	 * @param pathfile
	 * @param datebase
	 * @param temp
	 */
	public static void AddFolder(JLabel pathfile , DataBase datebase , DataBase temp) {
		String str = pathfile.getText();
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (datebase) {
					datebase.AddCollection(IOCsvFile.readfile(str));
					temp.AddDataBase(datebase);
				}

			}
		}).start();
		pathfile.setText("No Selection");


	}
	/**
	 * A shell function for adding filter
	 * @param pathfile
	 * @param filter
	 */
	public static void AddFilter(JLabel pathfile , Operator[] filter)
	{
		String str = pathfile.getText();
		String[] temp = str.split("\\\\");
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(temp[temp.length-1].startsWith("A"))
					filter[0] = (AndOperator)IOSerialization.ReadObject(str, "A");
				else if(temp[temp.length-1].startsWith("Non"))
					filter[0] = (NonOperator)IOSerialization.ReadObject(str, "Non");
				else if(temp[temp.length-1].startsWith("Not"))
					filter[0] = (NotOperator)IOSerialization.ReadObject(str, "Not");
				else
					filter[0] = (FilterNotFilterOperator)IOSerialization.ReadObject(str, "N");

			}
		}).start();
		pathfile.setText("No Selection");
	}
	/**
	 * A shell function for reset the database
	 * @param database
	 * @param temp
	 */
	public static void Reset(DataBase database , DataBase temp) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				database.Clear();
				temp.Clear();

			}
		}).start();	
	}
	/**
	 * A shell function for cancel the filter that done in the database
	 * @param database
	 * @param temp
	 */
	public static void CancelFilter(DataBase database , DataBase temp) {
		new Thread(new Runnable() {

			@Override
			public void run() {	
				database.Clear();
				database.AddDataBase(temp);				
			}
		}).start();
	}
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
	 * A shell function for show in google static map the result
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
	 * A shell function that use in the second algorithm
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
