package algorithm;
/**
 * A class that found the mac location base on the data base
 * and evaluates location algorithm
 * @author יובל מזרחי
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sample.Sample;
import sample.WayPoint;
import sample.Wifi;


public class MacLocation {
	public static ArrayList<Sample> findmaclocation(ArrayList<Sample> database)
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
		ArrayList<Sample> temp = new ArrayList<>();
		for(int i = 0; i < database.size() ; i++)
		{
			for(int j = 0; j < database.get(i).getArraywifi().size(); j++)
			{
				WayPoint point = findmaclocation(database.get(i).getArraywifi().get(j).getMac(), map);
				Sample t = new Sample(database.get(i), point, database.get(i).getArraywifi().get(0));
				temp.add(t);
			}
		}
		ArrayList<Sample> tempwithnodup = (ArrayList<Sample>) temp.stream().filter(distinctByKey(p -> p.getArraywifi().get(0).getMac()))
				.collect(Collectors.toList());
		return tempwithnodup;
	}

	/**
	 * A function that get from the user a mac and path
	 * and return the location revalued of the mac
	 * @param mac
	 * @param path
	 * @return way point of the mac
	 */
	private static WayPoint findmaclocation(String mac , Map<String, ArrayList<Sample>> map)
	{
		ArrayList<Sample> database = new ArrayList<>(map.get(mac));
		for(int i = 0; i < database.size() ; i++)
		{
			ArrayList<Wifi> wifi = new ArrayList<>(database.get(i).getArraywifi());
			int index = database.get(i).getArraywifi().indexOf(new Wifi(mac));
			wifi.add(0, wifi.get(index));
			wifi.remove(index+1);
			database.get(i).setArraywifi(wifi);
		}
		Collections.sort(database, (a,b) -> a.getArraywifi().get(0).compareTo(b.getArraywifi().get(0)));
		return FindLocation.locationbysingal(database);
	}
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
