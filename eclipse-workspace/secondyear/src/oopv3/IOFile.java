package oopv3;

/**
 * An interface that combines writing and reading functions
 * @author יובל מזרחי
 *
 */
public interface IOFile {
	/**
	 * A function that reads a file
	 */
	public void readfile();
	/**
	 * A function that accepts the name of a file to write in it
	 * and a list of samples and inserts the samples into the file
	 * @param filename
	 */
	public void writefile(String filename);

}
