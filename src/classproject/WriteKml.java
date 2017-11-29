package classproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
/**
 * A class for write kml file
 * @author יובל מזרחי
 *
 */
public class WriteKml {
	/**
	 * A function that accepts the name of a writable file and produces a kml file
	 */
	public static void writefile(String filename , ArrayList<Sample> files) {
		files = new Gui().startgui(files);
		final Kml kml = new Kml();
		Document doc=kml.createAndSetDocument();
		for(int i = 0; i < files.size() ; i++)
		{
			TimeStamp time = new TimeStamp();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time.setWhen(df.format(files.get(i).getDate()).replaceAll(" ", "T") + "Z");
			String sample = "Id: " + files.get(i).getId()
					+ "\nDate: " +df.format(files.get(i).getDate())
					+ "\nLat: " + files.get(i).getWaypoint().getLat().getCoordinate()
					+ "\nLon: " + files.get(i).getWaypoint().getLon().getCoordinate()
					+ "\nAlt: " + files.get(i).getWaypoint().getAlt().getCoordinate();
			for(int j = 0 ; j < files.get(i).getArraywifi().size() ; j++)
			{
				String wifi = "\nSsid: " + files.get(i).getArraywifi().get(j).getSsid()
						+ "\nMac: " + files.get(i).getArraywifi().get(j).getMac()
						+ "\nfrequncy: " + files.get(i).getArraywifi().get(j).getFrequncy()
						+ "\nSingal: " + files.get(i).getArraywifi().get(j).getSingal();
				doc.createAndAddPlacemark().withName(files.get(i).getId())
				.withDescription(sample + wifi).withTimePrimitive(time)
				.createAndSetPoint().addToCoordinates(files.get(i).getWaypoint().getLon().getCoordinate(), files.get(i).getWaypoint().getLat().getCoordinate());
			}
		}
		try {
			kml.marshal(new File("output/"+filename));
		} catch (FileNotFoundException e) {
			System.out.println("Exception thrown  :" + e);
		}
	}
	

}
