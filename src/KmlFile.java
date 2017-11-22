

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
 * A class that creates a filtered kml file
 * @author יובל מזרחי
 *
 */
public class KmlFile implements IOFile {
	private ArrayList<Sample> files;
	private String path;
	/**
	 * A constructor that receives the parameter path
	 * @param path
	 */
	public KmlFile(String path) {
		this.files = new ArrayList<>();
		this.path = path;
		readfile();
	}
	/**
	 * function that return the value of the arraylsit<sample>
	 * @return arraylsit<sample>
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
	/**
	 * function that return the value of the path
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * function that get path and change the path value
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * A function that reads a file according to the path received from the constructor
	 * and transforms the file into a set of samples
	 */
	@Override
	public void readfile() {
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader("output/"+this.path);
			br = new BufferedReader(fr);
			String str = br.readLine();
			str = br.readLine();
			while(str != null)
			{
				this.files.add(convertstringtosample(str));
				str = br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception thrown  :" + e);
		}


	}
	/**
	 * A function that accepts the name of a writable file and produces a kml file
	 */
	@Override
	public void writefile(String filename) {
		this.files = new Gui().startgui(files);
		final Kml kml = new Kml();
		Document doc=kml.createAndSetDocument();
		for(int i = 0; i < this.files.size() ; i++)
		{
			TimeStamp time = new TimeStamp();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time.setWhen(df.format(this.files.get(i).getDate()).replaceAll(" ", "T") + "Z");
			String sample = "Id: " + this.files.get(i).getId()
					+ "\nDate: " +df.format(this.files.get(i).getDate())
					+ "\nLat: " + this.files.get(i).getWaypoint().getLat().getCoordinate()
					+ "\nLon: " + this.files.get(i).getWaypoint().getLon().getCoordinate()
					+ "\nAlt: " + this.files.get(i).getWaypoint().getAlt().getCoordinate();
			for(int j = 0 ; j < this.files.get(i).getArraywifi().size() ; j++)
			{
				String wifi = "\nSsid: " + this.files.get(i).getArraywifi().get(j).getSingal()
						+ "\nMac: " + this.files.get(i).getArraywifi().get(j).getMac()
						+ "\nfrequncy: " + this.files.get(i).getArraywifi().get(j).getFrequncy()
						+ "\nSingal: " + this.files.get(i).getArraywifi().get(j).getSingal();
				doc.createAndAddPlacemark().withName(this.files.get(i).getId())
				.withDescription(sample + wifi).withTimePrimitive(time)
				.createAndSetPoint().addToCoordinates(this.files.get(i).getWaypoint().getLon().getCoordinate(), this.files.get(i).getWaypoint().getLat().getCoordinate());
			}
		}
		try {
			kml.marshal(new File("output/"+filename));
		} catch (FileNotFoundException e) {
			System.out.println("Exception thrown  :" + e);
		}
	}
	/**
	 * A function that accepts a string line and turns it into a sample
	 * @param row
	 * @return a sample
	 */
	private static Sample convertstringtosample(String row)
	{
		String[] temp = row.split(",");
		Sample sample = new Sample();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(temp[0]);
		} catch (ParseException e) {
			System.out.println("Exception thrown  :" + e);
		}
		temp[0] = temp[0].replaceAll(" ", "T");
		temp[0] = temp[0].replaceAll("/", ":");
		temp[0] = temp[0] + "Z";
		sample.setDate(date);
		sample.setId(temp[1]);
		sample.setWaypoint(new WayPoint(Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), Double.parseDouble(temp[4])));
		ArrayList<Wifi> wifis = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(temp[5]) ; i++ )
		{
			Wifi wifi = new Wifi();
			wifi.setSsid(temp[6 + i*4]);
			wifi.setMac(temp[7 + i*4]);
			wifi.setFrequncy(temp[8 + i*4]);
			wifi.setSingal(temp[9 + 4*i]);
			wifis.add(wifi);
		}
		sample.setArraywifi(wifis);
		return sample;
	}
}
