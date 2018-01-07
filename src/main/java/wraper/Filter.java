package wraper;

import database.DataBase;
import filter.Operator;
import filter.SamplePredicates;

public class Filter {
	/**
	 * A function that filter the database by the filter
	 * that given by the user
	 * @param filter
	 * @param database
	 */
	public static void FilterDataBase(Operator[] filter , DataBase database)
	{
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
