package classes;
/**
 * A class that contain the value of the coordinate 
 * @author יובל מזרחי
 *njbvjb
 */
public class Coordinate {
	private double coordinate;
	/**
	 * default constructor
	 */
	public Coordinate()
	{
		
	}
	/**
	 * A constructor that get a coordinate
	 * @param coordinate
	 */
	public Coordinate(double coordinate)
	{
		this.coordinate = coordinate;
	}
	/**
	 * function that return the value of the coordinate
	 * @return
	 */
	public double getCoordinate() {
		return coordinate;
	}
	/**
	 * function that get coordinate and change the coordinate value
	 * @param coordinate
	 */
	public void setCoordinate(double coordinate) {
		this.coordinate = coordinate;
	}
	/**
	 * A function that return true if both of them is equeal that return
	 * else return false
	 * @param obj
	 * @return
	 */
	public boolean equals(Coordinate obj) {
		if(this.coordinate == obj.getCoordinate())
			return true;
		return false;
	}
	/**
	 * A function that print the details of the coordinate
	 */
	@Override
	public String toString() {
		return this.coordinate+"";
	}
	

}
