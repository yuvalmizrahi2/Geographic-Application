package sample;

/**
 * A class that stores all the coordinates of the sample
 * @author יובל מזרחי
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
	public WayPoint(WayPoint point)
	{
		this.lat = new Coordinate(point.getLat().getCoordinate());
		this.lon = new Coordinate(point.getLon().getCoordinate());
		this.alt = new Coordinate(point.getAlt().getCoordinate());
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alt == null) ? 0 : alt.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
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
		WayPoint other = (WayPoint) obj;
		if (alt == null) {
			if (other.alt != null)
				return false;
		} else if (!alt.equals(other.alt))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
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
