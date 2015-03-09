
package adfs.projects

import org.apache.spark._
import org.apache.spark.SparkContext._

import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.PageRank

/** PAGE RANK FOR ADFS */
object SparkPageRankADFS {

  def adfsRun(sc: SparkContext, args: Array[String]) {
    val graph = GraphLoader.edgeListFile(sc, args(1))
    val toler = args(0).toDouble
    
    val ranks = graph.pageRank(toler).vertices
    //val ranks = PageRank.runUntilConvergence(graph, 0.0001).vertices

    ranks.saveAsTextFile(args(2))
  }

  def main(args: Array[String]) {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    adfsRun(sc, args)
  }

}
