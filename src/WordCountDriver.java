import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: WordCount <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        // Configure for local mode
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.set("mapreduce.jobtracker.address", "local");
        conf.setInt("mapreduce.task.io.sort.mb", 1);
        // Enable local file system
        conf.set("fs.file.impl", "org.apache.hadoop.fs.LocalFileSystem");
        
        Job job = Job.getInstance(conf, "word count");

        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Use absolute paths
        FileInputFormat.addInputPath(job, new Path(System.getProperty("user.dir") + "/" + args[0]));
        FileOutputFormat.setOutputPath(job, new Path(System.getProperty("user.dir") + "/" + args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

