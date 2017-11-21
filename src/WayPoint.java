
/**
 * A class that stores all the coordinates of the sample
 * @author יובל מזרחי
 *
 */
public class WayPoint {
	private coordinate lat;
	private coordinate lon;
	private coordinate alt;
	/**
	 * A constructor that receives the parameters lat,lon,alt
	 * @param lat
	 * @param lon
	 * @param alt
	 */
	public WayPoint(double lat, double lon, double alt) {
		super();
		this.lat = new coordinate(lat);
		this.lon = new coordinate(lon);
		this.alt = new coordinate(alt);
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
	public coordinate getLat() {
		return lat;
	}
	/**
	 * function that get lat and change the lat value
	 * @param lat
	 */
	public void setLat(coordinate lat) {
		this.lat = lat;
	}
	/**
	 * function that return the value of the lon
	 * @return lon
	 */
	public coordinate getLon() {
		return lon;
	}
	/**
	 * function that get lon and change the lon value
	 * @param lon
	 */
	public void setLon(coordinate lon) {
		this.lon = lon;
	}
	/**
	 * function that return the value of the alt
	 * @return alt
	 */
	public coordinate getAlt() {
		return alt;
	}
	/**
	 * function that get alt and change the alt value
	 * @param alt
	 */
	public void setAlt(coordinate alt) {
		this.alt = alt;
	}
	/**
	 * A function that makes a comparison between two way points
	 * The comparison is done according to alt,lat and lon
	 * @param obj
	 * @return true if equal else false
	 */
	public boolean equals(WayPoint obj) {
		if(!(this.alt.equals(obj.alt)))
			return false;
		if(!(this.lat.equals(obj.lat)))
			return false;
		if(!(this.lon.equals(obj.lon)))
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
