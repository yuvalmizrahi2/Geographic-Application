package filter;

import sample.Sample;
/**
 * A function that present date filter
 * @author יובל מזרחי
 *
 */
public class IdFilter implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7483130392388554952L;
	private String id;
	/**
	 * A constructor that get id
	 * @param f1
	 * @param f2
	 */
	public IdFilter(String id) {
		this.id = id;
	}
	/**
	 * A function that override the check function
	 * and return true if the filter is correct else false
	 */
	@Override
	public boolean check(Sample sample) {
		return sample.getId().equals(id);
	}
	/**
	 * A function that print the details of filter
	 */
	@Override
	public String toString() {
		return "Id(id="+id+")";
	}

}
