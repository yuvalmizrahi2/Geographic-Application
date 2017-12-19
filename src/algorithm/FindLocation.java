package algorithm;

import java.util.ArrayList;

import sample.Sample;
import sample.WayPoint;

/**
 * A class that evaluates location of the mac by alt,lat,lon and weight
 * @author יובל מזרחי
 *
 */
public class FindLocation {
	/**
	 * A function that return evaluates location by array list of samples
	 * and calculate the weight of each singal
	 * @param locationarray
	 * @return a way point of the mac
	 */
	public static WayPoint locationbysingal(ArrayList<Sample> locationarray)
	{
		int strongestsamples = AlgoMath.getStrongestsample();
		ArrayList<Double> weight = new ArrayList<>();
		for(int i = 0 ; i < strongestsamples && i < locationarray.size() ; i++)
		{
			weight.add(AlgoMath.weight(locationarray.get(i).getArraywifi().get(0).getSingal()));
		}
		return locationbyweight(locationarray, weight);
	}
	/**
	 * A function that return evaluates location by array list of samples
	 * and array list of weight
	 * @param locationarray
	 * @param weight
	 * @return a way point of the mac
	 */
	public static WayPoint locationbyweight(ArrayList<Sample> locationarray , ArrayList<Double> weight)
	{
		double sweight = 0, salt = 0, slon = 0, slat = 0;
		for(int i = 0 ; i < AlgoMath.getStrongestsample() && i < locationarray.size() ; i++)
		{
			sweight = sweight + weight.get(i);
			salt = salt + AlgoMath.coordinateweight(locationarray.get(i).getWaypoint().getAlt(), weight.get(i));
			slon = slon + AlgoMath.coordinateweight(locationarray.get(i).getWaypoint().getLon(), weight.get(i));
			slat = slat + AlgoMath.coordinateweight(locationarray.get(i).getWaypoint().getLat(), weight.get(i));
		}
		return new WayPoint(slat/sweight, slon/sweight, salt/sweight);
	}

}
