import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HourCountMapper2
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  String hour = null;
		 String userID = "";
		 String day = "";
		 int val = 0;
	  String line = value.toString();
	  String [] lines = line.split("\t");
	  
	  if(lines[0].length()>2){
		  String temp = lines[0].substring(0,2);
			temp = temp + "Count";
			context.write(new Text(temp), new IntWritable(1)); 
		} 
	  else{
		  hour = lines[0];
		  val = Integer.parseInt(lines[1]);
		  context.write(new Text(hour), new IntWritable(val));
	  }
	 // int sum = Integer.parseInt(lines[flag]);
	 //context.write(new Text(classAtt), new IntWritable(1));
	  //context.write(new Text(attrib), new IntWritable(1));
	  //context.write(new Text(attrib1), new IntWritable(1));
	  //context.write(new Text(finalSum), new IntWritable(1));
  }
}

