package database;

/**
 * A Class that contain all the details of the files in one place
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sample.Sample;

public class DataBase {
	private Set<Sample> arraysample;
	private Map<String, ArrayList<Sample>> map;

	/**
	 * Default constructor
	 */
	public DataBase()
	{
		this.arraysample = new HashSet<>();
		this.map = new HashMap<>();
	}
	/**
	 * A constructor that get a database
	 * @param temp
	 */
	public void AddDataBase(DataBase temp)
	{
		for (Sample sample : temp.arraysample) {
			this.Add(sample);
		}
	}
	/**
	 * A function that get sample and added it to the database
	 * @param sample
	 */
	public void Add(Sample sample)
	{
			this.arraysample.add(sample);
			for (Sample samp : arraysample) {
				for(int i = 0 ; i < samp.getArraywifi().size(); i++)
				{
					if(map.containsKey(samp.getArraywifi().get(i).getMac()))
					{
						map.get(samp.getArraywifi().get(i).getMac()).add(samp);
					}
					else
					{
						ArrayList<Sample> temp = new ArrayList<>();
						temp.add(samp);
						map.put(samp.getArraywifi().get(i).getMac(), temp);
					}
				}
			}
	}
	/**
	 * A function that get collection and added it to the database
	 * @param collection
	 */
	public void AddCollection(Collection<Sample> collection)
	{
		for (Sample sample : collection) {
			this.Add(sample); 
		}
	}
	/**
	 * A function that clear the database
	 */
	public void Clear()
	{
		this.arraysample.clear();
		this.map.clear();
	}
	/**
	 * A function that return set of sample
	 * @return
	 */
	public Set<Sample> getSetsample() {
		return arraysample;
	}
	/**
	 * A function that get set of sample and change the database by the new detials
	 * @param setsample
	 */
	public void setSetsample(Set<Sample> setsample) {
		this.arraysample.clear();
		this.map.clear();
		this.AddCollection(setsample);
	}
	/**
	 * A function that return all the macs in the database as map
	 * @return
	 */
	public Map<String, ArrayList<Sample>> getMap() {
		return map;
	}
	/**
	 * A function that get map of macs and change the database by the new detials
	 * @param map
	 */
	public void setMap(Map<String, ArrayList<Sample>> map) {
		this.map = map;
	}
}
