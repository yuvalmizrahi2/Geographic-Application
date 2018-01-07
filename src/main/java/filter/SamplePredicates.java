package filter;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import sample.Sample;
/**
 * A class that filters the list of samples by categories
 * @author יובל מזרחי
 *
 */
public class SamplePredicates {
	/**
	 * A function that receives an array of samples
	 * and filter the samples that don't contain replicate mac
	 * and save the strongest mac
	 * @param samples
	 * @return
	 */
	public static ArrayList<Sample> replicateMac(ArrayList<Sample> samples)
	{
		int index;
		for(int i = 0; i < samples.size() ; i++)
		{
			for(int j = 0; j < samples.get(i).getArraywifi().size() ; j++)
			{
				for(int k = 0; k < samples.size() ; k++)
				{
					if(k != i && samples.get(k).getArraywifi().contains(samples.get(i).getArraywifi().get(j)))
					{
						index = samples.get(k).getArraywifi().indexOf(samples.get(i).getArraywifi().get(j));
						if(samples.get(i).getArraywifi().get(j).compareTo(samples.get(k).getArraywifi().get(index)) >= 0)
						{
							samples.get(k).getArraywifi().remove(index);
						}
					}
				}
			}
		}
		return samples;
	}
	/**
	 * A functionthat get database and operator with filter and filter the database 
	 * @param samples
	 * @param operator
	 * @return
	 */
	public static Set<Sample> filterSampleWithOperator(Set<Sample> samples,Operator operator)
	{
		return  samples.stream().filter(s -> operator.check(s)).collect(Collectors.<Sample>toSet());
	}

}
