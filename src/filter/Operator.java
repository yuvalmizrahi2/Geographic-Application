package filter;
/**
 * * A interface for operator
 */
import java.io.Serializable;

import sample.Sample;

public interface Operator extends Serializable {
	/**
	 * A function that check if the sample is correct is some conditions
	 * @param sample
	 * @return
	 */
	public boolean check(Sample sample);

}
