package filter;

import sample.Sample;
/**
 * A class that behave as and operator
 * @author יובל מזרחי
 *
 */
public class FilterNotFilterOperator implements Operator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9205650248029856866L;
	private Filter filter1;
	private Filter filter2;
	/**
	 * Default constructor
	 */
	public FilterNotFilterOperator() {
		filter1 = null;
		filter2 = null;
	}
	/**
	 * A function that return the first filter
	 * @return
	 */
	public Filter getFilter1() {
		return filter1;
	}
	/**
	 * A function that set the first filter
	 * @param filter1
	 */
	public void setFilter1(Filter filter1) {
		this.filter1 = filter1;
	}
	/**
	 * A function that return the second filter
	 * @return
	 */
	public Filter getFilter2() {
		return filter2;
	}
	/**
	 * A function that set the second filter
	 * @param filter1
	 */
	public void setFilter2(Filter filter2) {
		this.filter2 = filter2;
	}
	/**
	 * A constructor that get two filter
	 * @param f1
	 * @param f2
	 */
	public FilterNotFilterOperator(Filter f1 , Filter f2)
	{
		this.filter1 = f1;
		this.filter2 = f2;
	}
	/**
	 * A function that override the check function
	 * and return true if both of the filter is correct else false
	 */
	@Override
	public boolean check(Sample sample) {
		return filter1.check(sample) &&(!filter1.check(sample) || !filter2.check(sample)); 
	}
	/**
	 * A function that print the details of operator
	 */
	@Override
	public String toString() {
		return "(" + filter1 + "!" + filter2 + ")";
	}

}
