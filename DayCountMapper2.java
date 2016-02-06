import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DayCountMapper2
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
		 String day = "";
		 int val = 0;
	  String line = value.toString();
	  String [] lines = line.split("\t");
	  
	  if(lines[0].length()>3){
		  String temp = lines[0].substring(0,3);
			temp = temp + "Count";
			context.write(new Text(temp), new IntWritable(1)); 
		} 
	  else{
		  day = lines[0];
		  val = Integer.parseInt(lines[1]);
		  context.write(new Text(day), new IntWritable(val));
	  }
	
  }
}

