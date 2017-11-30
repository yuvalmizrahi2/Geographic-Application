package classproject;

import java.util.ArrayList;

/**
 * A class that contain all the math function for 
 * @author יובל מזרחי
 *
 */
public class AlgoMath {
	/**
	 * A function that return the weight of the singal
	 * @param signal
	 * @return the weight by the power of signal
	 */
	public static double weight(int signal)
	{
		return 1/(signal*signal);
	}
	/**
	 * A function that return the weight of the coordinate
	 * @param coor
	 * @param weight
	 * @return the coordinate weight
	 */
	public static double coordinateweight(Coordinate coor , double weight)
	{
		return coor.getCoordinate()*weight;
	}
	/**
	 * A function that get arraylist of double and return the sum 
	 * of all the object
	 * @param listofweigt
	 * @return sum of the arraylist
	 */
	public static double sumofalltheweight(ArrayList<Double> listofweigt)
	{
		double sum = 0.0;
		for(int i = 0; i < listofweigt.size() ; i++)
		{
			sum = sum + listofweigt.get(i);
		}
		return sum;
	}

}
