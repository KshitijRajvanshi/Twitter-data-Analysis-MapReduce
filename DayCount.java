import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DayCount {

  public static void main(String[] args) throws Exception {
    if (args.length != 4) {
      System.err.println("Usage: WordCount <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = new Job();
    job.setJarByClass(DayCount.class);
    job.setJobName("Lines");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(DayCountMapper.class);
   // job.setCombinerClass(DayCountCombiner.class);
    job.setReducerClass(DayCountReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    if(job.waitForCompletion(true)){
        Job job1 = new Job();
        job1.setJarByClass(DayCount.class);
        job1.setJobName("SumofCounts");
        System.out.println("hahahaha");
        FileInputFormat.addInputPath(job1, new Path(args[2]));
        FileOutputFormat.setOutputPath(job1, new Path(args[3]));
        
        job1.setMapperClass(DayCountMapper2.class);
        job1.setReducerClass(DayCountReducer2.class);

        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        System.exit(job1.waitForCompletion(true) ? 0 : 1);
//      job1.submit();
       }
    

  }
}

