import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HourCountReducer2
extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		String tempKey = key.toString();
		int sum = 0;
		int count = 0;
			for (IntWritable value : values) {
	
				
				sum = sum + value.get();
			}
		/*if(tempKey.length()>2){
			String temp = tempKey.substring(0,2);
			temp = temp + "Count";
			context.write(new Text(temp), new IntWritable(count));
		}
		else*/
			context.write(key, new IntWritable(sum));

	}	
}

