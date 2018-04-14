package wordscountapp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;



public class KeyWordsWritable implements WritableComparable<KeyWordsWritable>{
	
	private Text state;
	private Text keyword;
	
	public KeyWordsWritable() {
		this.state = new Text();
		this.keyword = new Text();
	}
	
	//parameterized constructor
	public KeyWordsWritable(Text state,Text keyword) {
		this.state = state;
		this.keyword = keyword;
	}
	
	public Text getState() {
		return state;
	}

	public void setState(Text state) {
		this.state = state;
	}

	public Text getKeyword() {
		return keyword;
	}

	public void setKeyword(Text keyword) {
		this.keyword = keyword;
	}

	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.state.readFields(arg0);
        this.keyword.readFields(arg0);
		
	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.state.write(arg0);
        this.keyword.write(arg0);
	}

	public int compareTo(KeyWordsWritable o) {
		if (o == null) return 0;
		int difference = this.state.compareTo(o.state);
		return difference == 0 ? this.keyword.compareTo(o.keyword) : difference;
	}
	
	public String toString(){
		return this.keyword + "," + this.state;
	}

	public boolean equals(Object obj) {		
		if (obj == null) {
	        return false;
	    }
		
	    if (!KeyWordsWritable.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    
	    KeyWordsWritable newObject = (KeyWordsWritable) obj;
	    
	    if ((this.keyword == null) || (newObject.keyword != null) || (this.state == null) || (newObject.state != null)) {
	        return false;
	    }
	    
		if (this.state.equals(newObject.state) && this.keyword.equals(newObject.keyword)) {
			return true;
		}
		return false;
	}
}
