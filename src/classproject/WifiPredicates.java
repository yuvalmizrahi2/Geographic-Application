package classproject;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A class that filters the list of wifis by categories
 * @author יובל מזרחי
 *
 */
public class WifiPredicates {
	/**
	 * A function that receives an array of wifis
	 * and a filter type and returns the filtered array by type
	 * @param samples
	 * @param predicate
	 * @return filtered array
	 */
	public static ArrayList<Wifi> filterSample(ArrayList<Wifi> samples , Predicate<Wifi> predicate)
	{
		return (ArrayList<Wifi>) samples.stream().filter(predicate).collect(Collectors.<Wifi>toList());
	}
	/**
	 * A function that filters by mac
	 * @param id
	 * @return predicate<sample> filter by id
	 */
	public static Predicate<Wifi> isidequalto(String mac)
	{
		return p -> p.getMac().equals(mac);
	}

}
