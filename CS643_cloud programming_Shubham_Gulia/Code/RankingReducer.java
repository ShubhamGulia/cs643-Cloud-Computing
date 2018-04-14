package wordscountapp;

//import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.util.*;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.IOException;


public class RankingReducer extends MapReduceBase implements Reducer<Text, KeyWordsCatWritable, Text, Text>{

	public void reduce(Text key, Iterator<KeyWordsCatWritable> values, OutputCollector<Text, Text> output, Reporter r) throws IOException {
	
		//Store values into map
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while(values.hasNext()) {
			KeyWordsCatWritable kwcw = values.next();
			String keyword = kwcw.getState().toString();
			map.put(keyword, kwcw.getCount().get());
		}
		
		//Sorting the list
		Set<Entry<String, Integer>> set = map.entrySet();
        ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort(list, new OrderByCount());
        
        //output filter, top(3) of the result
        int i = 0;
		for(Map.Entry<String, Integer> entry:list){
			if (i >= 3) break;// top 3
			//output: [<0:keyword>, <1:state, 2:count>]
            output.collect(key, new Text(entry.getKey() + "," + entry.getValue()));
            i++;
        }
	}
}