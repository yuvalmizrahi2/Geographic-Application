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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		IdFilter other = (IdFilter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * A function that print the details of filter
	 */
	@Override
	public String toString() {
		return "Id(id="+id+")";
	}

}
