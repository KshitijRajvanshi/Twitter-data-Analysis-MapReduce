import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DayCountMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
		 String userID = "";
		 String day = "";
		 String uID = "";
	  String line = value.toString();
	  String [] lines = line.split(",\"");
	  for(int i=0;i<lines.length;i++){
			 
			 if (lines[i].length() > 10){
			 String temp = lines[i].substring(0, 10);
			 if(temp.equals("{\"created_")){
				 String[] hourLine = lines[i].split(" ");
				 day = hourLine[0].substring(15);
				 uID = hourLine[1]+hourLine[2] + hourLine[5].substring(0, 4);
				 uID = day + "_" + uID;
				/* for(int k=0;k<hourLine.length;k++){
					 System.out.println(hourLine[k]);
				 }*/
			 }
			 
			 if(temp.equals("user\":{\"id")){
				userID = lines[i].substring(12);
				// System.out.println(userID);
				// System.out.println(lines[i]);
				 break;
			 }
			 }
		
	  }	  
	  if(userID.equals("211178363")){
			context.write(new Text(day), new IntWritable(1));
			context.write(new Text(uID), new IntWritable(1));
			System.out.println(day+"_" + uID);
		}  
	 // int sum = Integer.parseInt(lines[flag]);
	 //context.write(new Text(classAtt), new IntWritable(1));
	  //context.write(new Text(attrib), new IntWritable(1));
	  //context.write(new Text(attrib1), new IntWritable(1));
	  //context.write(new Text(finalSum), new IntWritable(1));
  }
}

