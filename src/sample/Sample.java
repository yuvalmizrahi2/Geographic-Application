package sample;


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
	public Sample(Sample sample,WayPoint point , Wifi wifi)
	{
		this.id = sample.getId();
		this.Waypoint = new WayPoint(point);
		this.date = new Date();
		this.setDate(sample.getDate());
		this.arraywifi = new ArrayList<>();
		this.arraywifi.add(wifi);
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
		this.arraywifi.clear();
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sample other = (Sample) obj;
		if (Waypoint == null) {
			if (other.Waypoint != null)
				return false;
		} else if (!Waypoint.equals(other.Waypoint))
			return false;
		if (arraywifi == null) {
			if (other.arraywifi != null)
				return false;
		} else if (!arraywifi.equals(other.arraywifi))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * A function that compare two samples by their date
	 */
	@Override
	public int compareTo(Sample o) {
		return this.date.compareTo(o.date);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Waypoint == null) ? 0 : Waypoint.hashCode());
		result = prime * result + ((arraywifi == null) ? 0 : arraywifi.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
