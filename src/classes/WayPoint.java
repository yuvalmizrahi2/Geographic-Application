package classes;

/**
 * A class that stores all the coordinates of the sample
 * @author ���� �����
 *
 */
public class WayPoint {
	private Coordinate lat;
	private Coordinate lon;
	private Coordinate alt;
	/**
	 * A constructor that receives the parameters lat,lon,alt
	 * @param lat
	 * @param lon
	 * @param alt
	 */
	public WayPoint(double lat, double lon, double alt) {
		super();
		this.lat = new Coordinate(lat);
		this.lon = new Coordinate(lon);
		this.alt = new Coordinate(alt);
	}
	/**
	 * Default constructor
	 */
	public WayPoint() {
		super();
		this.lat = new Coordinate();
		this.lon = new Coordinate();
		this.alt = new Coordinate();
	}
	/**
	 * function that return the value of the lat
	 * @return lat
	 */
	public Coordinate getLat() {
		return lat;
	}
	/**
	 * function that get lat and change the lat value
	 * @param lat
	 */
	public void setLat(Coordinate lat) {
		this.lat = lat;
	}
	/**
	 * function that return the value of the lon
	 * @return lon
	 */
	public Coordinate getLon() {
		return lon;
	}
	/**
	 * function that get lon and change the lon value
	 * @param lon
	 */
	public void setLon(Coordinate lon) {
		this.lon = lon;
	}
	/**
	 * function that return the value of the alt
	 * @return alt
	 */
	public Coordinate getAlt() {
		return alt;
	}
	/**
	 * function that get alt and change the alt value
	 * @param alt
	 */
	public void setAlt(Coordinate alt) {
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
