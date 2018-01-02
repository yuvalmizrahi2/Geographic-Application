package inputoutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import filter.AndOperator;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
/**
 * A class that use the function of Serialization on the Filter class and his sons
 * @author יובל מזרחי
 *
 */
public class IOSerialization {
	/**
	 * A function that get operator file name and path and write the
	 * operator as txt file with Serialization
	 * @param filename
	 * @param filter
	 * @param dir
	 */
	public static void WriteObject(String filename , Operator filter , String dir)
	{
		FileOutputStream f = null;
		ObjectOutputStream o;
		try {
			f = new FileOutputStream(dir+"/"+filename);
			o = new ObjectOutputStream(f);
			o.writeObject(filter);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "error in writing filter", "Worng", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}


	}
	/**
	 * A function that get path and kind of operator as string and read
	 * the file with Serialization and cast the object to the correct operator
	 * @param path
	 * @param end
	 * @return
	 */
	public static Operator ReadObject(String path , String end)
	{
		FileInputStream fi;
		ObjectInputStream oi;
		Operator filter = null;

		try {
			fi = new FileInputStream(path);
			oi = new ObjectInputStream(fi);
			if(end.equals("A"))
				filter = (AndOperator)oi.readObject();
			else if(end.equals("Non"))
				filter = (NonOperator)oi.readObject();
			else if(end.equals("Not"))
				filter = (NotOperator)oi.readObject();
			else 
				filter = (FilterNotFilterOperator)oi.readObject();
			oi.close();
			fi.close();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "class not found", "Worng", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error in reading file", "Worng", JOptionPane.ERROR_MESSAGE);
		}
		return filter;
	}
}
