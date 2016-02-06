import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;


public class DayAverage {
	public static void main(String[] args) {
		Scanner s;
	double avg=0;
	Map<String,Double> map = new HashMap<String,Double>();
	ValueComparator bvc =  new ValueComparator(map);
    TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
			try {
				s = new Scanner(new File("/root/Downloads/javaTest/dayop2"));
				while(s.hasNext()){
					
				String lines = s.nextLine();
				String line [] = lines.split("\t");
				String day = line[0];
				//System.out.println(day);
				int noOfDays = Integer.parseInt(line[1]);
				//System.out.println(noOfDays);
				String nextLines = s.nextLine();
				String nextline [] = nextLines.split("\t");
				int count = Integer.parseInt(nextline[1]);
				//System.out.println(count);
				avg = noOfDays*1.0/count;
				map.put(day, avg);
				System.out.println("Average of " + day + ": " + avg);
				
				}	
				sorted_map.putAll(map);
				
				Entry entry = sorted_map.firstEntry();
				System.out.println("@PrezOno tweets the maximum on " + entry.getKey() + "day. The average is: " + entry.getValue());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
class ValueComparator implements Comparator<String> {

    Map<String, Double> base;
    public ValueComparator(Map<String, Double> map) {
        this.base = map;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}