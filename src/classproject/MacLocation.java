package classproject;
/**
 * A class that found the mac location base on the data base
 * and evaluates location algorithm
 * @author יובל מזרחי
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class MacLocation {
	/**
	 * A function that get from the user a mac and path
	 * and return the location revalued of the mac
	 * @param mac
	 * @param path
	 * @return way point of the mac
	 */
	public static WayPoint findmaclocation(String mac , String path)
	{
		ArrayList<Sample> database = CsvToSamples.readfile(path);
		ArrayList<String> macs = new ArrayList<>();
		macs.add(mac);
		database = SamplePredicates.filterSample(database, SamplePredicates.wificontainmac(macs));
		for(int i = 0; i < database.size() ; i++)
		{
			database.get(i).setArraywifi(WifiPredicates.filterWifi(database.get(i).getArraywifi(), WifiPredicates.isidequalto(macs)));
		}
		Collections.sort(database, (a,b) -> a.getArraywifi().get(0).compareTo(b.getArraywifi().get(0)));
		return FindLocation.locationbysingal(database);
	}
}
