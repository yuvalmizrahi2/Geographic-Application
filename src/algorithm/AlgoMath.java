package algorithm;

import sample.Coordinate;

/**
 * A class that contain all the math function for 
 * @author יובל מזרחי
 *
 */
public class AlgoMath {
	private static int strongestsample = 3;
	private static int power = 2;
	private static int norm = 10000;
	private static double sigdiff = 0.4;
	private static int mindiff = 3;
	private static double nosingal = -120;
	private static double diffnosig = 100;


	/**
	 * A function that return the weight of the singal
	 * @param signal
	 * @return the weight by the power of signal
	 */
	public static double weight(double signal)
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
	 * amount of the strongest samples that need to take in the algorithm
	 * @return strongest samples
	 */
	/**
	 * A function that return the amount of sample
	 * that we need to check in our algorithm 
	 * @return num of samples
	 */
	public static int getStrongestsample() {
		return strongestsample;
	}
	/**
	 * A function that return the signal if the mac is not found
	 * @return nosingal
	 */
	public static double getnosingal()
	{
		return nosingal;
	}
	/**
	 * A function that return the diff between two signal
	 * if the data equal to nosingal that it wil return diffnosig
	 * @param singal
	 * @param data
	 * @return diff
	 */
	private static double getdiff(double singal , double data)
	{
		if(data == nosingal)
			return diffnosig;
		return Math.abs(singal-data) + mindiff;
	}
	/**
	 * A function that return the result of the first function in the algorithm
	 * @param diff
	 * @return diff pow sigdiff
	 */
	private static double f1(double diff)
	{
		return Math.pow(diff, sigdiff);
	}
	/**
	 * A function that return the result of the second function in the algorithm
	 * @param singal
	 * @return signal pow power
	 */
	private static double f2(double singal)
	{
		return Math.pow(singal, power);
	}
	/**
	 * A function that return the weight of the diff between signal and data
	 * @param singal
	 * @param data
	 * @return weight
	 */
	public static double weightdiff(double singal , double data)
	{
		return norm/(f1(getdiff(singal, data))*f2(singal));
	}

}
