package wraper;

import java.util.ArrayList;

import database.DataBase;
import filter.Operator;
import inputoutput.IOCsvFile;
import inputoutput.IOSerialization;
import inputoutput.WriteKml;

/**
 * A class that contain all the functions that connected to saving object
 * @author יובל מזרחי
 *
 */
public class Save {
	/**
	 * A function that saving the filter as the filter show in the memory
	 * @param dir
	 * @param namefile
	 * @param filter
	 * @param start
	 */
	public static void SaveFilter(String dir , String namefile,Operator filter,String start)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				IOSerialization.WriteObject(start+"-"+namefile+".txt", filter, dir);

			}
		}).start();
	}
	/**
	 * A function that saving the database in csv format
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveCsvFile(String dir , String namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					IOCsvFile.writefile(namefile+".csv", new ArrayList<>(database.getSetsample()),dir);
				}				
			}
		}).start();
	}
	/**
	 * A function that saving the database as kml format
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveKmlFile(String dir , String namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					WriteKml.writefile(namefile+".kml", new ArrayList<>(database.getSetsample()), dir);
				}

			}
		}).start();
	}

}
