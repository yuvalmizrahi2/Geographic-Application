package filter;

import sample.Sample;
/**
 * A class that behave as and operator
 * @author יובל מזרחי
 *
 */
public class NotOperator implements Operator , Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 854663640945820598L;
	private Filter filter;
	/**
	 * Default constructor
	 */
	public NotOperator() {
		filter = null;
	}
	/**
	 * A function that return the filter
	 * @return
	 */
	public Filter getFilter() {
		return filter;
	}
	/**
	 * A function that set the filter
	 * @param filter1
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	/**
	 * A constructor that get filter
	 * @param f1
	 * @param f2
	 */
	public NotOperator(Filter f)
	{
		this.filter = f;
	}
	/**
	 * A function that override the check function
	 * and return true if both of the filter is correct else false
	 */
	@Override
	public boolean check(Sample sample) {
		return !filter.check(sample);
	}
	/**
	 * A function that print the details of operator
	 */
	@Override
	public String toString() {
		return "(!" +filter+ ")";
	}
}
