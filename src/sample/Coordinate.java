package sample;
/**
 * A class that contain the value of the coordinate 
 * @author יובל מזרחי
 *
 */
public class Coordinate {
	private double coordinate;
	/**
	 * default constructor
	 */
	public Coordinate()
	{
		coordinate = 0;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coordinate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(coordinate) != Double.doubleToLongBits(other.coordinate))
			return false;
		return true;
	}
	/**
	 * A function that print the details of the coordinate
	 */
	@Override
	public String toString() {
		return this.coordinate+"";
	}
	

}
