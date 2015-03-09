
package adfs.projects

import org.apache.spark._
import org.apache.spark.SparkContext._

import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.PageRank

/** LOG HANDLING FOR ADFS */
object SparkLogHandADFS {

  def adfsRun(sc: SparkContext, args: Array[String]) {

    // --- FILTER ---
    val file = sc.textFile(args(2))

    val edges = file.flatMap(line => line.split("\n"))
                    .map(item => {val link = item.split("\t"); Edge(link(1).toLong, link(2).toLong, null)})

    val vertices = edges.flatMap(x=>List(x.srcId,x.dstId))
                    .distinct
                    .map(x=>(x,null))

    // --- PAGERANK ---
    val graph = Graph(vertices, edges)
    val toler = args(0).toDouble
    
    val ranks = graph.pageRank(toler).vertices

    // --- JOIN ---
    val names = sc.textFile(args(1))

    val ranksRDD = ranks

    val namesRDD = names.flatMap(lines => lines.split("\n"))
                    .map(line => {val item = line.split(" "); (item(0).toLong, item(1))})

    val joinRDD = ranksRDD.join(namesRDD)
    joinRDD.saveAsTextFile(args(3))
  }

  def main(args: Array[String]) {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    adfsRun(sc, args)
  }

}
