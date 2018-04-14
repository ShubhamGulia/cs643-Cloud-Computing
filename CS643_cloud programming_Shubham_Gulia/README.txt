
hdfs namenode -format

start-dfs.sh

start-yarn.sh

//run jps on both node data and name 
jps


// execution

//remove input file 
hadoop fs -rm -r /crazy

//remove output file
 hadoop fs -rm -r output

hdfs dfs -mkdir /crazy

//copying files 
hdfs dfs -put '/home/ec2-user/states/' /crazy/

hadoop jar sg952.jar wordscountapp.WordsCountApp /crazy/states output

hadoop fs -cat output/Ranking/part-00000

hadoop fs -cat output/KeyWordsCount/part-00000

