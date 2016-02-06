import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;


public class HourAverage {
	public static void main(String[] args) {
		Scanner s;
		double avg=0;
		Map<String,Double> map = new HashMap<String,Double>();
		ValueComparator bvc =  new ValueComparator(map);
		TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
		try {
			s = new Scanner(new File("/root/Downloads/javaTest/op2"));
			while(s.hasNext()){

				String lines = s.nextLine();
				String line [] = lines.split("\t");
				String hour = line[0];
				int noOfHours = Integer.parseInt(line[1]);
				String nextLines = s.nextLine();
				String nextline [] = nextLines.split("\t");
				int count = Integer.parseInt(nextline[1]);
				avg = noOfHours*1.0/count;
				System.out.println("Average of " + hour + ": " + avg);
				map.put(hour, avg);
			}
			sorted_map.putAll(map);

			Entry entry = sorted_map.firstEntry();
			System.out.println("@PrezOno tweets the maximum in the " + entry.getKey() + "th Hour. The average is: " + entry.getValue());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
