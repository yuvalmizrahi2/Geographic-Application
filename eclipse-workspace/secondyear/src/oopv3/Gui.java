package oopv3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * A department that performs all the user interface of filter selection
 * and the data required for each filter
 * @author יובל מזרחי
 *
 */
public class Gui {
	/**
	 * A function that calls all other functions in the class
	 * @param samples
	 * @return filter arraylist of samples
	 */
	public static ArrayList<Sample> startgui(ArrayList<Sample> samples)
	{
		Filter filter = choosefilter();
		switch (filter.getFilter()) {
		case TIME:
			samples = choosetwodate(samples);
			break;
		case ID:
			samples = chooseid(samples);
			break;
		default:
			samples = choosewaypointandradius(samples);
			break;
		}
		return samples;
	}
	/**
	 * The function asks the user to choose which filter type he wants
	 * @return the choose number
	 */
	private static Filter choosefilter()
	{
		Filter filter = new Filter();
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a filter : ");
		System.out.println("1. by gps");
		System.out.println("2. by time");
		System.out.println("3. by id");
		System.out.println("Enter a number filter: ");
		try {
			n = sc.nextInt();
			if(n > 3 || n <= 0)
			{
				sc.close();
				throw new Exception("worng input");
			}
			else
			{
				switch(n)
				{
				case 1:
					filter.setFilter(KindFilter.WAYPOINT);
					break;
				case 2:
					filter.setFilter(KindFilter.TIME);
					break;
				default:
					filter.setFilter(KindFilter.ID);
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		sc.close();
		return filter;
	}
	/**
	 * A function that asks the user to enter two dates
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private static ArrayList<Sample> choosetwodate(ArrayList<Sample> samples)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date[] date = {new Date() , new Date()};
		Scanner sc = new Scanner(System.in);
		String temp;
		try
		{
			System.out.println("enter the first date in the format yyyy-MM-dd HH:mm:ss :");
			temp = sc.next();
			if(!isValidFormat(temp))
			{
				sc.close();
				throw new Exception("The date is not in the correct format");
			}
			date[0] = df.parse(temp);
			System.out.println("enter the second date in the format yyyy-MM-dd HH:mm:ss :");
			temp = sc.next();
			if(!isValidFormat(temp))
			{
				sc.close();
				throw new Exception("The date is not in the correct format");
			}
			date[1] = df.parse(temp);

		}
		catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		sc.close();
		if(date[0].before(date[1]))
			return SamplePredicates.filterSample(samples, SamplePredicates.isinthetime(date[0], date[1]));
		return SamplePredicates.filterSample(samples, SamplePredicates.isinthetime(date[1], date[0]));
	}	
	/**
	 * A function that checks the format of the date
	 * @param value
	 * @return true if the date is equal to the value false if else
	 */
	private static boolean isValidFormat(String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return date != null;
	}
	/**
	 * A function that asks the user to enter id
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private static ArrayList<Sample> chooseid(ArrayList<Sample> samples)
	{
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.print("enter a id : ");
		id = sc.next();
		sc.close();
		return SamplePredicates.filterSample(samples, SamplePredicates.isidequalto(id));
	}
	/**
	 * A function that asks the user to enter way point and radius
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private static ArrayList<Sample> choosewaypointandradius(ArrayList<Sample> samples)
	{
		WayPoint point = new WayPoint();
		Scanner sc = new Scanner(System.in);
		double radius;
		try
		{
			System.out.println("enter the lat :");
			point.setLat(Double.parseDouble(sc.next()));
			System.out.println("enter the lon :");
			point.setLon(Double.parseDouble(sc.next()));
			System.out.println("enter the radius");
			radius = Double.parseDouble(sc.next());
			samples = SamplePredicates.filterSample(samples, SamplePredicates.isintheradius(point, radius));
		}
		catch (Exception e) {
			System.out.println("Exception thrown  :" + e);
		}
		sc.close();
		return samples;
	}
}
