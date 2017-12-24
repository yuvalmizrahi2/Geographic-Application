package filter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sample.Sample;
import sample.WayPoint;
/**
 * A class that filters the list of samples by categories
 * @author יובל מזרחי
 *
 */
public class SamplePredicates {
	/**
	 * A function that filters by id
	 * @param id
	 * @return predicate<sample> filter by id
	 */
	public static Predicate<Sample> isidequalto(String id , boolean negat)
	{
		if(negat)
			return p -> !(p.getId().equals(id));
		return p -> p.getId().equals(id);
	}
	/**
	 * A function that filters by waypoint and radius
	 * @param waypoint
	 * @param radius
	 * @return predicate<sample> filter by distance
	 */
	public static Predicate<Sample> isintheradius(WayPoint waypoint , Double radius , boolean negat)
	{
		if(negat)
			return p -> !(Point2D.distance(waypoint.getLat().getCoordinate(), waypoint.getLon().getCoordinate(), p.getWaypoint().getLat().getCoordinate(), p.getWaypoint().getLon().getCoordinate()) <= radius);
		return p -> Point2D.distance(waypoint.getLat().getCoordinate(), waypoint.getLon().getCoordinate(), p.getWaypoint().getLat().getCoordinate(), p.getWaypoint().getLon().getCoordinate()) <= radius;
	}
	/**
	 * A function that filters by date
	 * @param min
	 * @param max
	 * @return predicate<sample> filter by date
	 */
	public static Predicate<Sample> isinthetime(Date min , Date max , boolean negat)
	{
		if(negat)
			return p -> !(p.getDate().after(min)&&p.getDate().before(max));
		return p -> p.getDate().after(min)&&p.getDate().before(max);
	}
	/**
	 * A function that receives an array of samples
	 * and a filter type and returns the filtered array by type
	 * @param samples
	 * @param predicate
	 * @return filtered array
	 */
	public static ArrayList<Sample> filterSample(ArrayList<Sample> samples , Predicate<Sample> predicate)
	{
		return (ArrayList<Sample>) samples.stream().filter(predicate).collect(Collectors.<Sample>toList());
	}
	/**
	 * A function that receives an array of samples
	 * and a filter by two filters in the operation OR type and returns the filtered array by type
	 * @param samples
	 * @param predicate1
	 * @param predicate2
	 * @return
	 */
	public static ArrayList<Sample> filterSampleOr(ArrayList<Sample> samples , Predicate<Sample> predicate1,Predicate<Sample> predicate2)
	{
		return (ArrayList<Sample>) samples.stream().filter(predicate1.or(predicate2)).collect(Collectors.<Sample>toList());
	}
	/**
	 * A function that receives an array of samples
	 * and a filter by two filters in the operation AND type and returns the filtered array by type
	 * @param samples
	 * @param predicate1
	 * @param predicate2
	 * @return
	 */
	public static ArrayList<Sample> filterSampleNot(ArrayList<Sample> samples , Predicate<Sample> predicate1,Predicate<Sample> predicate2)
	{
		return (ArrayList<Sample>) samples.stream().filter(predicate1.and(predicate1.negate().or(predicate2).negate())).collect(Collectors.<Sample>toList());
	}
	/**
	 * A function that receives an array of samples
	 * and a filter by two filters in the operation AND type and returns the filtered array by type
	 * @param samples
	 * @param predicate1
	 * @param predicate2
	 * @return
	 */
	public static ArrayList<Sample> filterSampleAnd(ArrayList<Sample> samples , Predicate<Sample> predicate1,Predicate<Sample> predicate2)
	{
		return (ArrayList<Sample>) samples.stream().filter(predicate1.and(predicate2)).collect(Collectors.<Sample>toList());
	}
	/**
	 * A function that receives an array of samples
	 * and filter the samples that don't contain replicate mac
	 * and save the strongest mac
	 * @param samples
	 * @return
	 */
	public static ArrayList<Sample> replicateMac(ArrayList<Sample> samples)
	{
		int index;
		for(int i = 0; i < samples.size() ; i++)
		{
			for(int j = 0; j < samples.get(i).getArraywifi().size() ; j++)
			{
				for(int k = 0; k < samples.size() ; k++)
				{
					if(k != i && samples.get(k).getArraywifi().contains(samples.get(i).getArraywifi().get(j)))
					{
						index = samples.get(k).getArraywifi().indexOf(samples.get(i).getArraywifi().get(j));
						if(samples.get(i).getArraywifi().get(j).compareTo(samples.get(k).getArraywifi().get(index)) >= 0)
						{
							samples.get(k).getArraywifi().remove(index);
						}
					}
				}
			}
		}
		return samples;
	}

}
