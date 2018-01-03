package wraper;

import database.DataBase;

/**
 * A class that contain all the functions that connected
 * to the database
 * @author יובל מזרחי
 *
 */
public class Database {
	/**
	 * A function that reset the database
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
	 * A function that cancel the filters that the user had done in the databse
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

}
