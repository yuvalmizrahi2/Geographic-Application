package wraper;

import database.DataBase;
import filter.AndOperator;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import inputoutput.CsvToSamples;
import inputoutput.IOCsvFile;
import inputoutput.IOSerialization;
/**
 * A class that contain all the functions that connected
 * to adding object to the system
 * @author יובל מזרחי
 *
 */
public class Add {
	/**
	 * A function that get from the user file in 46 format and added it to the database
	 * @param pathfile
	 * @param database
	 * @param temp
	 */
	public static void AddCsv(String pathfile , DataBase database , DataBase temp) {
		String str = pathfile;
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					database.AddCollection(CsvToSamples.readfile(str));
					temp.AddDataBase(database);
				}

			}
		}).start();
	}
	/**
	 * A function that get from the user a folder that contion csv files in wigle
	 * format and added them to the database
	 * @param pathfile
	 * @param datebase
	 * @param temp
	 */
	public static void AddFolder(String pathfile , DataBase datebase , DataBase temp) {
		String str = pathfile;
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (datebase) {
					datebase.AddCollection(IOCsvFile.readfile(str));
					temp.AddDataBase(datebase);
				}

			}
		}).start();
	}
	/**
	 * A function that get from the user a file txt that contain 
	 * detail of filter and read it to the filter details in the 
	 * system
	 * @param pathfile
	 * @param filter
	 */
	public static void AddFilter(String pathfile , Operator[] filter)
	{
		String str = pathfile;
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
	}

}
