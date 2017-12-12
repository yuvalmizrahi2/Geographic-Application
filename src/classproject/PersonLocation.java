package classproject;

import java.util.ArrayList;
import java.util.Collections;
/**
 * A class that evaluates location of the person by alt,lat,lon and weight 
 * @author יובל מזרחי
 *
 */

public class PersonLocation {
	public static void findpersonlocation(ArrayList<Sample> database , ArrayList<Sample> files)
	{
		WayPoint point;
		ArrayList<String> mac = new ArrayList<>();
		ArrayList<Double> signal = new ArrayList<>();
		for(int i = 0; i < files.size() ; i++)
		{
			for(int j = 0; j < files.get(i).getArraywifi().size() ; j++)
			{
				mac.add(files.get(i).getArraywifi().get(j).getMac());
				signal.add(files.get(i).getArraywifi().get(j).getSingal());
			}
			point = findpersonlocation(mac, signal, database);
			files.get(i).setWaypoint(point);
			mac.clear();
			signal.clear();
		}
	}

	/**
	 * A function that get from the user a array list of mac,signal and path
	 * and return the location revalued of the person
	 * @param mac
	 * @param singal
	 * @param path
	 * @return a way point
	 */
	private static WayPoint findpersonlocation(ArrayList<String> mac , ArrayList<Double> signal , ArrayList<Sample> data)
	{
		ArrayList<Sample> database = new ArrayList<>(data);
		database = SamplePredicates.filterSample(database, SamplePredicates.wificontainmac(mac));
		System.out.println(database.size());
		for(int i = 0; i < database.size() ; i++)
		{
			database.get(i).setArraywifi(WifiPredicates.filterWifi(database.get(i).getArraywifi(), WifiPredicates.isidequalto(mac)));
		}
		ArrayList<Double> pi = new ArrayList<>();
		for(int j = 0; j < database.size() ; j++)
		{
			pi.add(calculatepi(database.get(j), mac, signal));
		}
		return sortbypi(database, pi);
	}
	/**
	 * A function that caluclate the pi of the sample
	 * @param sample
	 * @param mac
	 * @param singal
	 * @return weight
	 */
	private static double calculatepi(Sample sample , ArrayList<String> mac , ArrayList<Double> singal)
	{
		int index;
		double pi = 1.0;
		for(int i =0 ; i< mac.size() ; i++)
		{
			if(sample.getArraywifi().contains(new Wifi(mac.get(i))))
			{
				index = sample.getArraywifi().indexOf(new Wifi(mac.get(i)));
				pi = pi * AlgoMath.weightdiff(singal.get(i), sample.getArraywifi().get(index).getSingal());
			}
			else
			{
				pi = pi * AlgoMath.weightdiff(singal.get(i), AlgoMath.getnosingal());
			}
		}
		return pi;
	}
	/**
	 * A function that get array list of sample(database) and array list of double(pi)
	 * and sort the pi and in the same time save the order in the database
	 * @param database
	 * @param pi
	 * @return a way point
	 */
	private static WayPoint sortbypi(ArrayList<Sample> database , ArrayList<Double> pi)
	{
		for(int i=0;i<pi.size();i++){
			for(int j=i+1;j<pi.size();j++){
				if(pi.get(i)<pi.get(j)){
					Collections.swap(pi, i, j);
					Collections.swap(database, i, j);
				}
			}
		}
		return FindLocation.locationbyweight(database, pi);
	}

}
