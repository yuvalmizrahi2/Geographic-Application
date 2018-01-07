package gui;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import database.DataBase;
import filter.Operator;
import wraper.Add;
import wraper.Algorithm;
import wraper.Database;
import wraper.Filter;
import wraper.Listener;
import wraper.Save;
/**
 * A class that contaion all the functions that connection between the gui and the other project's codes
 * @author יובל מזרחי
 *
 */
public class Wraper {
	/**
	 * A shell function for saving filter
	 * @param dir
	 * @param namefile
	 * @param filter
	 * @param start
	 */
	public static void SaveFilter(String dir , String namefile,Operator filter,String start)
	{
		Save.SaveFilter(dir, namefile, filter, start);
	}
	/**
	 * A shell function for saving csv file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveCsvFile(String dir , String namefile,DataBase database)
	{
		Save.SaveCsvFile(dir, namefile, database);

	}
	/**
	 * A shell function for saving kml file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveKmlFile(String dir , String namefile,DataBase database)
	{
		Save.SaveKmlFile(dir, namefile, database);
	}
	/**
	 * A shell function for filter the database by a filter
	 * @param filter
	 * @param database
	 */
	public static void FilterDataBase(Operator[] filter , DataBase database)
	{
		Filter.FilterDataBase(filter, database);
	}
	/**
	 * A shell function for adding csv file
	 * @param pathfile
	 * @param database
	 * @param temp
	 */
	public static void AddCsv(String pathfile , DataBase database , DataBase temp) {
		Add.AddCsv(pathfile, database, temp);
	}
	/**
	 * A shell function for adding folder that contain wigle files
	 * @param pathfile
	 * @param datebase
	 * @param temp
	 */
	public static void AddFolder(String pathfile , DataBase datebase , DataBase temp) {
		Add.AddFolder(pathfile, datebase, temp);
	}
	/**
	 * A shell function for adding filter
	 * @param pathfile
	 * @param filter
	 */
	public static void AddFilter(String pathfile , Operator[] filter)
	{
		Add.AddFilter(pathfile, filter);
	}
	/**
	 * A shell function for reset the database
	 * @param database
	 * @param temp
	 */
	public static void Reset(DataBase database , DataBase temp) {
		Database.Reset(database, temp);
	}
	/**
	 * A shell function for cancel the filter that done in the database
	 * @param database
	 * @param temp
	 */
	public static void CancelFilter(DataBase database , DataBase temp) {
		Database.CancelFilter(database, temp);
	}
	/**
	 * A shell function for show in google static map the result
	 * of the first algorithm
	 * @param database
	 * @param mac
	 * @return
	 */
	public static ImageIcon GetMapLocation(DataBase database , String mac)
	{
		return Algorithm.GetMapLocation(database, mac);
	}
	/**
	 * A shell function that use in the second algorithm
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	public static ImageIcon Algo2(String string , DataBase database)
	{
		return Algorithm.Algo2(string, database);
	}
	/**
	 * A function that return the result of the second algorithm
	 * on google static map
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	public static ImageIcon Algo2(ArrayList<String> mac , ArrayList<Double> signal , DataBase database)
	{
		return Algorithm.Algo2(mac, signal, database);
	}
	/**
	 * A shell function for listen for files
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 * @throws InterruptedException
	 */
	public static void fileslisten(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> file) throws InterruptedException {
		Listener.fileslisten(database, temp, folder, file);

	}
	/**
	 * A sheel function for listen to folder
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	public static void folderlisten(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> file) {
		Listener.folderlisten(database, temp, folder, file);
	}
}
