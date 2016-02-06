import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HourCount {

  public static void main(String[] args) throws Exception {
    if (args.length != 4) {
      System.err.println("Usage: WordCount <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = new Job();
    job.setJarByClass(HourCount.class);
    job.setJobName("Lines");
    //String args0 = args[0];
    //String args1 = args[1];
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(HourCountMapper.class);
    //job.setCombinerClass(HourCountCombiner.class);
    job.setReducerClass(HourCountReducer.class);
    System.out.println("hehe: $" + args[0] + "$" + args[1] + "$" + args[2] + "$" + args[3]);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
   // job.submit();
    //System.exit(job.waitForCompletion(true) ? 0 : 1);
    if(job.waitForCompletion(true)){
    Job job1 = new Job();
    job1.setJarByClass(HourCount.class);
    job1.setJobName("SumofCounts");
    System.out.println("hahahaha");
    FileInputFormat.addInputPath(job1, new Path(args[2]));
    FileOutputFormat.setOutputPath(job1, new Path(args[3]));
    
    job1.setMapperClass(HourCountMapper2.class);
    job1.setReducerClass(HourCountReducer2.class);

    job1.setOutputKeyClass(Text.class);
    job1.setOutputValueClass(IntWritable.class);
    System.exit(job1.waitForCompletion(true) ? 0 : 1);
//    job1.submit();
   }
    
    
  }
}

