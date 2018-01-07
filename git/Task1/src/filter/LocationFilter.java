package filter;

import java.awt.geom.Point2D;

import sample.Sample;
import sample.WayPoint;
/**
 * A function that present date filter
 * @author יובל מזרחי
 *
 */
public class LocationFilter implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 526235263617637908L;
	private WayPoint point;
	private double radius;
	/**
	 * A constructor that get waypoint and raduis
	 * @param f1
	 * @param f2
	 */
	public LocationFilter(WayPoint point , double radius) {
		this.point = new WayPoint(point);
		this.radius = radius;
	}
	/**
	 * A function that override the check function
	 * and return true if the filter is correct else false
	 */
	@Override
	public boolean check(Sample sample) {
		return Point2D.distance(point.getLat().getCoordinate(), point.getLon().getCoordinate(), sample.getWaypoint().getLat().getCoordinate(), sample.getWaypoint().getLon().getCoordinate()) <= radius;
	}
	/**
	 * A function that print the details of filter
	 */
	@Override
	public String toString() {
		return "Location(radius<="+this.radius+",center=("+point.getLat()+","+point.getLon()+"))";
	}

}
