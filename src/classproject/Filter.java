package classproject;

/**
 * A class that preserves the type of filtering it chooses
 * @author יובל מזרחי
 *
 */
public class Filter {
	private KindFilter filter;
	/**
	 * Default constructor
	 */
	public Filter() 
	{

	}
	/**
	 * A constructor that receives the parameter filter
	 * @param filter
	 */
	public Filter(KindFilter filter) {
		this.filter = filter;
	}
	/**
	 * function that return the value of the filter
	 * @return
	 */
	public KindFilter getFilter() {
		return filter;
	}
	/**
	 * function that get filter and change the filter value
	 * @param filter
	 */
	public void setFilter(KindFilter filter) {
		this.filter = filter;
	}

}
