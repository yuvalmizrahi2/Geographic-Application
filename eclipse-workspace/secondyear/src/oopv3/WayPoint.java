package oopv3;
/**
 * A class that stores all the coordinates of the sample
 * @author יובל מזרחי
 *
 */
public class WayPoint {
	private double lat;
	private double lon;
	private double alt;
	/**
	 * A constructor that receives the parameters lat,lon,alt
	 * @param lat
	 * @param lon
	 * @param alt
	 */
	public WayPoint(double lat, double lon, double alt) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}
	/**
	 * Default constructor
	 */
	public WayPoint() {
		super();
	}
	/**
	 * function that return the value of the lat
	 * @return lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * function that get lat and change the lat value
	 * @param lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * function that return the value of the lon
	 * @return lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * function that get lon and change the lon value
	 * @param lon
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * function that return the value of the alt
	 * @return alt
	 */
	public double getAlt() {
		return alt;
	}
	/**
	 * function that get alt and change the alt value
	 * @param alt
	 */
	public void setAlt(double alt) {
		this.alt = alt;
	}
	/**
	 * A function that makes a comparison between two way points
	 * The comparison is done according to alt,lat and lon
	 * @param obj
	 * @return true if equal else false
	 */
	public boolean equals(WayPoint obj) {
		if(!(this.alt == obj.alt))
			return false;
		if(!(this.lat == obj.lat))
			return false;
		if(!(this.lon == obj.lon))
			return false;
		return true;
	}
	/**
	 * A function that print the details of the way point
	 */
	@Override
	public String toString() {
		return this.lat+","+this.lon+","+this.alt;
	}

}
