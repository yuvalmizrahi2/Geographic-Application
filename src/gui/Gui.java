package gui;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import filter.Filter;
import filter.KindFilter;
import filter.SamplePredicates;
import sample.Sample;
import sample.WayPoint;

/**
 * A department that performs all the user interface of filter selection
 * and the data required for each filter
 * @author יובל מזרחי
 *
 */
public class Gui {
	private Scanner scanner;
	/**
	 * A function that calls all other functions in the class
	 * @param samples
	 * @return filter arraylist of samples
	 */
	/**
	 * Default constructor
	 */
	public Gui()
	{
		this.scanner = new Scanner(System.in);
	}
	/**
	 * function that return the value of the scanner
	 * @return scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}
	/**
	 * function that get scanner and change the scanner value
	 * @param sc
	 */
	public void setscanner(Scanner sc) {
		this.scanner = sc;
	}
	/**
	 * function that start the Gui
	 * @param samples
	 * @return
	 */
	public ArrayList<Sample> startgui(ArrayList<Sample> samples)
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
		return SamplePredicates.replicateMac(samples);
	}
	/**
	 * The function asks the user to choose which filter type he wants
	 * @return the choose number
	 */
	private Filter choosefilter()
	{
		Filter filter = new Filter();
		int n;
		System.out.println("choose a filter : ");
		System.out.println("1. by gps");
		System.out.println("2. by time");
		System.out.println("3. by id");
		System.out.println("Enter a number filter: ");
		try {
			n = this.scanner.nextInt();
			if(n > 3 || n <= 0)
			{
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
		return filter;
	}
	/**
	 * A function that asks the user to enter two dates
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private ArrayList<Sample> choosetwodate(ArrayList<Sample> samples)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date[] date = {new Date() , new Date()};
		String temp = "";
		try
		{
			System.out.println("enter the first date in the format yyyy-MM-dd HH:mm:ss :");
			temp = this.scanner.next();
			temp = temp + " " + this.scanner.next();
			date[0] = df.parse(temp);
			System.out.println("enter the second date in the format yyyy-MM-dd HH:mm:ss :");
			temp = this.scanner.next();
			temp = temp + " " + this.scanner.next();
			date[1] = df.parse(temp);

		}
		catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		if(date[0].before(date[1]))
			return SamplePredicates.filterSample(samples, SamplePredicates.isinthetime(date[0], date[1],false));
		return SamplePredicates.filterSample(samples, SamplePredicates.isinthetime(date[1], date[0],false));
	}	
	/**
	 * A function that asks the user to enter id
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private ArrayList<Sample> chooseid(ArrayList<Sample> samples)
	{
		String id;
		System.out.print("enter a id : ");
		id = this.scanner.next();
		return SamplePredicates.filterSample(samples, SamplePredicates.isidequalto(id,false));
	}
	/**
	 * A function that asks the user to enter way point and radius
	 * @param samples
	 * @return filter arraylist of sample
	 */
	private ArrayList<Sample> choosewaypointandradius(ArrayList<Sample> samples)
	{
		WayPoint point = new WayPoint();
		double radius;
		try
		{
			System.out.println("enter the lat :");
			point.getLat().setCoordinate(Double.parseDouble(this.scanner.next()));
			System.out.println("enter the lon :");
			point.getLon().setCoordinate(Double.parseDouble(this.scanner.next()));
			System.out.println("enter the radius");
			radius = Double.parseDouble(this.scanner.next());
			samples = SamplePredicates.filterSample(samples, SamplePredicates.isintheradius(point, radius,false));
		}
		catch (Exception e) {
			System.out.println("Exception thrown  :" + e);
		}
		return samples;
	}
}
