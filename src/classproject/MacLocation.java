package classproject;
/**
 * A class that found the mac location base on the data base
 * and evaluates location algorithm
 * @author יובל מזרחי
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MacLocation {
	public static ArrayList<Sample> findmaclocation(ArrayList<Sample> files)
	{
		ArrayList<Sample> temp = new ArrayList<>();
		for(int i = 0; i < files.size() ; i++)
		{
			for(int j = 0; j < files.get(i).getArraywifi().size(); j++)
			{
				WayPoint point = findmaclocation(files.get(i).getArraywifi().get(j).getMac(), files);
				temp.add(new Sample(files.get(i), point, files.get(i).getArraywifi().get(j)));
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
	private static WayPoint findmaclocation(String mac , ArrayList<Sample> files)
	{
		ArrayList<Sample> database = new ArrayList<>(files);
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
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
