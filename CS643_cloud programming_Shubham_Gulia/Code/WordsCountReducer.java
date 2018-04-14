package wordscountapp;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;



public class WordsCountReducer extends MapReduceBase implements Reducer<KeyWordsWritable, IntWritable,KeyWordsWritable,IntWritable>{

	public void reduce(KeyWordsWritable key, Iterator<IntWritable> value, OutputCollector<KeyWordsWritable, IntWritable> output, Reporter r) throws IOException {
		
		int totalCount = 0;
		while(value.hasNext()) {
			totalCount += value.next().get();//summarize all counts
		}
		//output: [<0:keyword, 1:state>, <2:count>]
		output.collect(key, new IntWritable(totalCount));
	}
}
