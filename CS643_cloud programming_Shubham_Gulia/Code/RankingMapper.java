package wordscountapp;

import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;



public class RankingMapper extends MapReduceBase implements Mapper<Object, Text, Text, KeyWordsCatWritable>{ 
	public void map(Object key, Text value, OutputCollector<Text,KeyWordsCatWritable> output, Reporter r) throws IOException {
		
		String keywordCountStrArr [] = key.toString().split(",");
		//output: [<0:keyword>, <1:state, 2:count>]
		output.collect(new Text(keywordCountStrArr[0]), new KeyWordsCatWritable(new Text(keywordCountStrArr[1]), new IntWritable(Integer.parseInt(keywordCountStrArr[2]))));
	}
}
