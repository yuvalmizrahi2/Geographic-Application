

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * A class that contains all the sampling data including the wifi data of that sample
 * @author יובל מזרחי
 *
 */
public class Sample implements Comparable<Sample> {
	private String id;
	private WayPoint Waypoint;
	private Date date;
	private ArrayList<Wifi> arraywifi;
	/**
	 * Default constructor
	 */
	public Sample()
	{
		this.arraywifi = new ArrayList<>();
		this.Waypoint = new WayPoint();
	}
	/**
	 * function that return the value of the date
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * function that get date and change the date value
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * function that return arraylist<wifi>
	 * @return arraylist<wifi>
	 */
	public ArrayList<Wifi> getArraywifi() {
		return arraywifi;
	}
	/**
	 * function that get arraylist<wifi> and change the value of arraywifi
	 * @param arraywifi
	 */
	public void setArraywifi(ArrayList<Wifi> arraywifi) {
		this.arraywifi.addAll(arraywifi);
	}
	/**
	 * function that return the value of the id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * function that get id and change the id value
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * function that return the value of the waypoint
	 * @return waypoint
	 */
	public WayPoint getWaypoint() {
		return Waypoint;
	}
	/**
	 * function that get waypoint and change the waypoint value
	 * @param waypoint
	 */
	public void setWaypoint(WayPoint waypoint) {
		Waypoint = waypoint;
	}
	/**
	 * A function that makes a comparison between two sample not including wifi data
	 * The comparison is done according to date and waypoint 
	 * @param temp
	 * @return true if equal else false
	 */
	@Override
	public boolean equals(Object temp) {
		Sample sample = (Sample)temp;
		return this.Waypoint.equals(sample.Waypoint) &&
				this.date.equals(sample.date);
	}
	/**
	 * A function that compare two samples by their date
	 */
	@Override
	public int compareTo(Sample o) {
		return this.date.compareTo(o.date);
	}
	/**
	 * A function that print the details of the sample
	 */
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(this.date.getTime())+","+this.id+","+this.Waypoint.toString()+",";
	}


}
