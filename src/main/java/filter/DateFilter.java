package filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import sample.Sample;
/**
 * A function that present date filter
 * @author יובל מזרחי
 *
 */
public class DateFilter implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3226969456481470457L;
	private Date mindate;
	private Date maxdate;
	/**
	 * A constructor that get two date
	 * @param f1
	 * @param f2
	 */
	public DateFilter(Date min , Date max) {
		this.mindate = min;
		this.maxdate = max;
	}
	/**
	 * A function that override the check function
	 * and return true if the filter is correct else false
	 */
	@Override
	public boolean check(Sample sample) {
		return sample.getDate().after(mindate)&&sample.getDate().before(maxdate);
	}
	/**
	 * A function that print the details of filter
	 */
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Time("+dateFormat.format(mindate)+"<date<"+dateFormat.format(maxdate)+")";
	}

}
