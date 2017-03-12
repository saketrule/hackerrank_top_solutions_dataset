import java.util.ArrayList
import java.util.PriorityQueue
import scala.collection.JavaConversions._

object Solution {

    def main(args: Array[String]) {
        val line = readLine.split(" ").map(_.toInt)
        val graph = new Graph(line(0))
            
        for (i <- 0 until line(1)) {
           val edge = readLine.split(" ").map(_.toInt)
           graph.add(edge(0) -1, edge(1) - 1, edge(2))
        }   
        
        println(mst(graph))
    }
    
    def mst(graph: Graph): Int = {
        val visited = new Array[Boolean](graph.V)
        visited(0) = true    
        val q = new PriorityQueue[Edge]
        var mst = 0
        var count = 1
            
        for (i <- 0 until graph.adj(0).size) {
            q.offer(new Edge(0, graph.adj(0).get(i), graph.weight(0).get(i)))
        }    
        
        while(count < graph.V && !q.isEmpty) {
            val edge = q.poll
            
            if (!visited(edge.v) || !visited(edge.w)) {
               mst += edge.weight    
                    
               val vertex = if (visited(edge.v)) edge.w else edge.v
               visited(vertex) = true   
               count += 1
               
               for (i <- 0 until graph.adj(vertex).size) {
                   q.offer(new Edge(vertex, graph.adj(vertex).get(i), graph.weight(vertex).get(i)))
               }
            }
        }
        
        mst
    }
    
}

class Graph(n: Int) {
    val adjacent = Array.fill[ArrayList[Int]](n)(new ArrayList[Int]())
    val weightList = Array.fill[ArrayList[Int]](n)(new ArrayList[Int])
        
    def add(v: Int, w: Int, weigh: Int): Unit = {
        adjacent(v).add(w)
        adjacent(w).add(v)
        weightList(v).add(weigh)
        weightList(w).add(weigh)       
    }    
    
    def adj(v: Int): ArrayList[Int] = adjacent(v)
        
    def weight(v: Int): ArrayList[Int] = weightList(v)
        
    def V(): Int = n
}

class Edge (val v:Int, val w:Int, val weight:Int) extends Comparable[Edge] {
    def compareTo(other: Edge):Int = {
        return weight.compareTo(other.weight)
    }
}


