package gui;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import algorithm.MacLocation;
import algorithm.PersonLocation;
import database.DataBase;
import filter.AndOperator;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.SamplePredicates;
import inputoutput.CsvToSamples;
import inputoutput.IOCsvFile;
import inputoutput.IOSerialization;
import inputoutput.WriteKml;
import sample.Sample;
import sample.WayPoint;
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
		new Thread(new Runnable() {

			@Override
			public void run() {
				IOSerialization.WriteObject(start+"-"+namefile+".txt", filter, dir);

			}
		}).start();
	}
	/**
	 * A shell function for saving csv file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveCsvFile(String dir , String namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					IOCsvFile.writefile(namefile+".csv", new ArrayList<>(database.getSetsample()),dir);
				}				
			}
		}).start();

	}
	/**
	 * A shell function for saving kml file
	 * @param dir
	 * @param namefile
	 * @param database
	 */
	public static void SaveKmlFile(String dir , String namefile,DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					WriteKml.writefile(namefile+".kml", new ArrayList<>(database.getSetsample()), dir);
				}

			}
		}).start();
	}
	/**
	 * A shell function for filter the database by a filter
	 * @param filter
	 * @param database
	 */
	public static void FilterDataBase(Operator[] filter , DataBase database)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					database.setSetsample(SamplePredicates.filterSampleWithOperator(database.getSetsample(), filter[0])); 
				}
			}
		}).start();
	}
	/**
	 * A shell function for adding csv file
	 * @param pathfile
	 * @param database
	 * @param temp
	 */
	public static void AddCsv(String pathfile , DataBase database , DataBase temp) {
		String str = pathfile;
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (database) {
					database.AddCollection(CsvToSamples.readfile(str));
					temp.AddDataBase(database);
				}

			}
		}).start();
	}
	/**
	 * A shell function for adding folder that contain wigle files
	 * @param pathfile
	 * @param datebase
	 * @param temp
	 */
	public static void AddFolder(String pathfile , DataBase datebase , DataBase temp) {
		String str = pathfile;
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (datebase) {
					datebase.AddCollection(IOCsvFile.readfile(str));
					temp.AddDataBase(datebase);
				}

			}
		}).start();


	}
	/**
	 * A shell function for adding filter
	 * @param pathfile
	 * @param filter
	 */
	public static void AddFilter(String pathfile , Operator[] filter)
	{
		String str = pathfile;
		String[] temp = str.split("\\\\");
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(temp[temp.length-1].startsWith("A"))
					filter[0] = (AndOperator)IOSerialization.ReadObject(str, "A");
				else if(temp[temp.length-1].startsWith("Non"))
					filter[0] = (NonOperator)IOSerialization.ReadObject(str, "Non");
				else if(temp[temp.length-1].startsWith("Not"))
					filter[0] = (NotOperator)IOSerialization.ReadObject(str, "Not");
				else
					filter[0] = (FilterNotFilterOperator)IOSerialization.ReadObject(str, "N");

			}
		}).start();
	}
	/**
	 * A shell function for reset the database
	 * @param database
	 * @param temp
	 */
	public static void Reset(DataBase database , DataBase temp) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				database.Clear();
				temp.Clear();

			}
		}).start();	
	}
	/**
	 * A shell function for cancel the filter that done in the database
	 * @param database
	 * @param temp
	 */
	public static void CancelFilter(DataBase database , DataBase temp) {
		new Thread(new Runnable() {

			@Override
			public void run() {	
				database.Clear();
				database.AddDataBase(temp);				
			}
		}).start();
	}
	/**
	 * A shell function that use in the first algorithm
	 * @param database
	 * @param mac
	 * @return
	 */
	private static WayPoint Algo1(DataBase database , String mac) {
		WayPoint[] point = new WayPoint[1];

		WayPoint temp = MacLocation.findmaclocation(mac, database.getMap());
		point[0] = new WayPoint(temp);
		return point[0];
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
		WayPoint point = new WayPoint(Algo1(database, mac));
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error in reading file", "Worng", JOptionPane.ERROR_MESSAGE);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
	}
	/**
	 * A shell function for convert a string to sample
	 * @param str
	 * @param database
	 * @return
	 */
	private static WayPoint StringToSample (String str , DataBase database) {
		Sample sample = CsvToSamples.convertstringtosample(str);
		ArrayList<String> mac = new ArrayList<>();
		ArrayList<Double> signal = new ArrayList<>();
		for(int i = 0 ; i < sample.getArraywifi().size() ; i++)
		{
			mac.add(sample.getArraywifi().get(i).getMac());
			signal.add(sample.getArraywifi().get(i).getSingal());
		}
		return PersonLocation.findpersonlocation(mac, signal, database.getMap());
	}
	/**
	 * A shell function that use in the second algorithm
	 * @param mac
	 * @param signal
	 * @param database
	 * @return
	 */
	private static WayPoint ThreeMac (ArrayList<String> mac , ArrayList<Double> signal , DataBase database) {
		return PersonLocation.findpersonlocation(mac, signal, database.getMap());
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
		WayPoint point = StringToSample(string, database);
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error in reading file", "Worng", JOptionPane.ERROR_MESSAGE);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
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
		WayPoint point = ThreeMac(mac, signal, database);
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+point.getLat()+","+point.getLon()+"&zoom=17&size=612x612&scale=2&maptype=satellite"
					+ "&markers=color:red%7C|label:L|%7C"+point.getLat()+","+point.getLon();
			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "error in reading file", "Worng", JOptionPane.ERROR_MESSAGE);
		}

		return new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH));
	}
	/**
	 * A function that check if files source database has changed
	 * @param database
	 * @param temp
	 * @param folder
	 * @param paths
	 */
	private static void checkchangeinfiles(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> paths) {
		int amountofpaths = paths.size();
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<Long> lastmodified = new ArrayList<>();
		for(int i = 0 ; i < paths.size() ; i++)
		{
			lastmodified.add(new File(paths.get(i)).lastModified());
		}
		service.submit(new Runnable() {

			@Override
			public void run() {
				while(Thread.interrupted() == false)
				{
					for(int i = 0 ; i < lastmodified.size() ; i++)
					{
						if(lastmodified.get(i) != new File(paths.get(i)).lastModified())
						{
							resetdatabasae(database, temp, folder, paths);
							service.shutdownNow();
							Thread.currentThread().interrupt();
							checkchangeinfiles(database , temp , folder , paths);
						}
					}
					if(amountofpaths != paths.size())
					{
						service.shutdownNow();
						Thread.currentThread().interrupt();
						checkchangeinfiles(database , temp , folder , paths);
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
	public static void fileslisten(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> file) throws InterruptedException {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				checkchangeinfiles(database , temp , folder , file);

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
	private static void checkchangeinfolder(DataBase database , DataBase temp , ArrayList<String> paths , ArrayList<String> file) throws Exception {
		int amountofpaths = paths.size();
		ExecutorService service = Executors.newCachedThreadPool();
		final FileSystem fs = FileSystems.getDefault();
		final java.nio.file.WatchService ws = fs.newWatchService();
		final Map<WatchKey, Path> keys = new ConcurrentHashMap<>();
		for(int i = 0 ; i < paths.size() ; i++)
			reg(fs.getPath(paths.get(i)), keys, ws);
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
						resetdatabasae(database, temp, paths, file);
						service.shutdownNow();
						Thread.currentThread().interrupt();
						try {
							checkchangeinfolder(database , temp , paths , file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(amountofpaths != paths.size()) {
						try {
							ws.close();
							service.shutdownNow();
							Thread.currentThread().interrupt();
							checkchangeinfolder(database , temp , paths , file);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
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
	 * A sheel function for listen to folder
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	public static void folderlisten(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> file) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					checkchangeinfolder(database , temp , folder , file);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		t.start();
	}
	/**
	 * A function that reset the database if have change in the file or the folder
	 * source database
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	private static void resetdatabasae(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> file)
	{
		synchronized (database) {
			database.Clear();
			temp.Clear();
			for(int i = 0 ; i < folder.size() ; i++)
			{
				AddFolder(folder.get(i), database, temp);
			}
			for(int i = 0  ; i< file.size() ; i++)
			{
				AddCsv(file.get(i), database, temp);
			}
		}
	}
}
