package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sample.Sample;
import sample.WayPoint;
import sample.Wifi;
/**
 * A class that evaluates location of the person by alt,lat,lon and weight 
 * @author יובל מזרחי
 *
 */

public class PersonLocation {
	/**
	 * A function that get database in array list tan move to map 
	 * @param database
	 * @param files
	 */
	public static void findpersonlocation(ArrayList<Sample> database , ArrayList<Sample> files)
	{
        Map<String, ArrayList<Sample>> map = new HashMap<String, ArrayList<Sample>>();
        for(int i = 0 ; i < database.size() ; i++)
        {
        	for(int j = 0; j < database.get(i).getArraywifi().size() ; j++)
        	{
        		if(map.containsKey(database.get(i).getArraywifi().get(j).getMac()))
        		{
        			map.get(database.get(i).getArraywifi().get(j).getMac()).add(database.get(i));
        		}
        		else
        		{
        			ArrayList<Sample> temp = new ArrayList<>();
        			temp.add(database.get(i));
        			map.put(database.get(i).getArraywifi().get(j).getMac(), temp);
        		}
        	}
        }
		ArrayList<String> mac = new ArrayList<>();
		ArrayList<Double> signal = new ArrayList<>();
		for(int i = 0; i < files.size() ; i++)
		{
			for(int j = 0; j < files.get(i).getArraywifi().size() ; j++)
			{
				mac.add(files.get(i).getArraywifi().get(j).getMac());
				signal.add(files.get(i).getArraywifi().get(j).getSingal());
			}
			files.get(i).setWaypoint(findpersonlocation(mac, signal, map));
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
	public static WayPoint findpersonlocation(ArrayList<String> mac , ArrayList<Double> signal , Map<String, ArrayList<Sample>> map)
	{
		ArrayList<Sample> database = new ArrayList<>();
		for(int i = 0; i < mac.size() ; i++)
		{
			if(map.containsKey(mac.get(i)))
			{
				database.addAll(map.get(mac.get(i)));
			}
		}
		Set<Sample> set = new HashSet<>();
		set.addAll(database);
		database.clear();
		database.addAll(set);
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
