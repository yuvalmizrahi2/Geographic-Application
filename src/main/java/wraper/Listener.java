package wraper;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DataBase;
import sample.Sample;
import sample.WayPoint;
import sample.Wifi;
import sql.IOSqlTable;

/**
 * A class that contain all the functions that connected to listen to files or filders changes
 * @author יובל מזרחי
 *
 */
public class Listener {
	/**
	 * A function that check if files source database has changed
	 * @param database
	 * @param temp
	 * @param folder
	 * @param paths
	 */
	private static void checkchangeinfiles(DataBase database) {
		int amountofpaths[] = {database.getFile().size()};
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<Long> lastmodified = new ArrayList<>();
		for(int i = 0 ; i < database.getFile().size() ; i++)
		{
			lastmodified.add(new File(database.getFile().get(i)).lastModified());
		}
		service.submit(new Runnable() {

			@Override
			public void run() {
				while(Thread.interrupted() == false)
				{
					for(int i = 0 ; i < lastmodified.size() ; i++)
					{
						if(lastmodified.get(i) != new File(database.getFile().get(i)).lastModified())
						{
							resetdatabasae(database);
							lastmodified.set(i, new File(database.getFile().get(i)).lastModified()) ;
						}
					}
					if(amountofpaths[0] != database.getFile().size())
					{
						for(int i = amountofpaths[0]; i < database.getFile().size() ; i++)
						{
							lastmodified.add(new File(database.getFile().get(i)).lastModified());
						}
						amountofpaths[0] = database.getFile().size();
					}
				}
			}
		});
	}
	/**
	 * A shell function for listen for files
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 * @throws InterruptedException
	 */
	public static void fileslisten(DataBase database) throws InterruptedException {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				checkchangeinfiles(database);

			}
		});
		t.start();

	}
	/**
	 * A function that check if the folder source database has changed
	 * @param database
	 * @param temp
	 * @param paths
	 * @param file
	 * @throws Exception
	 */
	private static void checkchangeinfolder(DataBase database) throws Exception {
		int amountofpaths[] = {database.getFolder().size()};
		ExecutorService service = Executors.newCachedThreadPool();
		final FileSystem fs = FileSystems.getDefault();
		final java.nio.file.WatchService ws = fs.newWatchService();
		final Map<WatchKey, Path> keys = new ConcurrentHashMap<>();
		for(int i = 0 ; i < database.getFolder().size() ; i++)
			reg(fs.getPath(database.getFolder().get(i)), keys, ws);
		service.submit(new Runnable() {
			@Override
			public void run() {
				while (Thread.interrupted() == false) {
					WatchKey key;
					try {
						key = ws.poll(10, TimeUnit.MILLISECONDS);
					} catch (InterruptedException | ClosedWatchServiceException e) {
						break;
					}
					if (key != null) {
						resetdatabasae(database);
						key.reset();
					}
					else if(amountofpaths[0] != database.getFolder().size()) {
						for(int i = amountofpaths[0] ; i < database.getFolder().size() ; i++)
						{
							try {
								reg(fs.getPath(database.getFolder().get(i)), keys, ws);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						amountofpaths[0] = database.getFolder().size();	
					}
				}
			}
		});
	}
	private static void reg(Path dir, Map<WatchKey, Path> keys, java.nio.file.WatchService ws)
			throws IOException {
		WatchKey key = dir.register(ws, ENTRY_CREATE, ENTRY_DELETE,
				ENTRY_MODIFY);
		keys.put(key, dir);
	}
	/**
	 * A shell function for listen to folder
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	public static void folderlisten(DataBase database) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					checkchangeinfolder(database);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		t.start();
	}
	/**
	 * A shell function for listen to sql table
	 * @param database
	 */
	public static void sqltablelisten(DataBase database) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				checkchangeinsqltable(database);

			}
		}).start();

	}
	private static String connectsql(DataBase database , int i) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(database.getSqltables().get(i).getUrl(), database.getSqltables().get(i).getUsername(), database.getSqltables().get(i).getPassword());
			st = con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '" + database.getSqltables().get(i).getNamedatabase() +"' AND TABLE_NAME = '"+database.getSqltables().get(i).getTablename()+"'");
			return (rs.getString(1));
		}
		catch (SQLException ex) {
			Logger lgr = Logger.getLogger(IOSqlTable.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (con != null) { con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(IOSqlTable.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return null;

	}
	/**
	 * A function that check if files source database has changed
	 * @param database
	 * @param temp
	 * @param folder
	 * @param paths
	 */
	private static void checkchangeinsqltable(DataBase database) {
		int amountofpaths[] = {database.getSqltables().size()};
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<String> lastmodified = new ArrayList<>();
		for(int i = 0 ; i < database.getFile().size() ; i++)
		{
			lastmodified.add(connectsql(database, i));
		}	
		service.submit(new Runnable() {

			@Override
			public void run() {
				while(Thread.interrupted() == false)
				{
					for(int i = 0 ; i < lastmodified.size() ; i++)
					{
						if(lastmodified.get(i) != connectsql(database, i))
						{
							resetdatabasae(database);
							lastmodified.set(i, connectsql(database, i)) ;
						}
					}
					if(amountofpaths[0] != database.getFile().size())
					{
						for(int i = amountofpaths[0]; i < database.getFile().size() ; i++)
						{
							lastmodified.add(connectsql(database, i));
							System.out.println(lastmodified.get(i));
						}
						amountofpaths[0] = database.getFile().size();
					}
				}
			}
		});
	}
	/**
	 * A function that reset the database if have change in the file or the folder
	 * source database
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	private static void resetdatabasae(DataBase database)
	{
		synchronized (database) {
			database.Clear();
			for(int i = 0 ; i < database.getFolder().size() ; i++)
			{
				Add.AddFolder(database.getFolder().get(i), database);
			}
			for(int i = 0  ; i< database.getFile().size() ; i++)
			{
				Add.AddCsv(database.getFile().get(i), database);
			}
		}
	}

}
