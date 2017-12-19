package kindfile;

import java.util.ArrayList;

import sample.Sample;
/**
 * A abstract class for the database of the files
 * @author יובל מזרחי
 *
 */
public abstract class File {
	protected ArrayList<Sample> files;
	/**
	 * A function that return the value of the array list
	 * @return array list
	 */
	public ArrayList<Sample> getFiles() {
		return files;
	}
	/**
	 * function that get arraylist<sample> and change the arraylist<sample> value
	 * @param files
	 */
	public void setFiles(ArrayList<Sample> files) {
		this.files = files;
	}

}
