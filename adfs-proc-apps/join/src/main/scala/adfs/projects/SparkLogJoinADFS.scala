
package adfs.projects

import org.apache.spark._
import org.apache.spark.SparkContext._

/** LOG JOIN FOR ADFS */
object SparkLogJoinADFS {

  def adfsRun(sc: SparkContext, args: Array[String]) {
    val names = sc.textFile(args(0))
    val pr = sc.textFile(args(1))

    val prRDD = pr.flatMap(lines => lines.split("\n"))
                    .map(line => {val nodes = line.split(","); (nodes(0).drop(1), nodes(1).dropRight(1))})

    val namesRDD = names.flatMap(lines => lines.split("\n"))
                    .map(line => {val item = line.split(" "); (item(0), item(1))})

    val joinRDD = prRDD.join(namesRDD)
    joinRDD.saveAsTextFile(args(2))
  }

  def main(args: Array[String]) {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    adfsRun(sc, args)
  }

}
