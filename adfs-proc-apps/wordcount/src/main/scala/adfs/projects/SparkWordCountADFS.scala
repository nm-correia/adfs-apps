
package adfs.projects

import org.apache.spark._
import org.apache.spark.SparkContext._
//import java.io.PrintWriter

/** WORD COUNT FOR ADFS */
object SparkWordCountADFS {

  def adfsRun(sc: SparkContext, args: Array[String]) {
    val file = sc.textFile(args(0))
    val counts = file.flatMap(line => line.split(" "))
                 .map(word => (word, 1))
                 .reduceByKey(_ + _)
    
    // Folder
    counts.saveAsTextFile(args(1))
    
    // File
    //val writer = new PrintWriter(args(1))
    //val output = counts.collect
    //output.foreach(tup => writer.write(tup._1 + " has rank: " + tup._2 + ".\n"))
    //writer.close
  }

  def main(args: Array[String]) {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    adfsRun(sc, args)
  }

}
