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
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import database.DataBase;

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
	private static void checkchangeinfiles(DataBase database , DataBase temp , ArrayList<String> folder , ArrayList<String> paths) {
		int amountofpaths[] = {paths.size()};
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
							lastmodified.set(i, new File(paths.get(i)).lastModified()) ;
						}
					}
					if(amountofpaths[0] != paths.size())
					{
						for(int i = amountofpaths[0]; i < paths.size() ; i++)
						{
							lastmodified.add(new File(paths.get(i)).lastModified());
						}
						amountofpaths[0] = paths.size();
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
		int amountofpaths[] = {paths.size()};
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
						key.reset();
					}
					else if(amountofpaths[0] != paths.size()) {
						for(int i = amountofpaths[0] ; i < paths.size() ; i++)
						{
							try {
								reg(fs.getPath(paths.get(i)), keys, ws);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						amountofpaths[0] = paths.size();	
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
				Add.AddFolder(folder.get(i), database, temp);
			}
			for(int i = 0  ; i< file.size() ; i++)
			{
				Add.AddCsv(file.get(i), database, temp);
			}
		}
	}

}
