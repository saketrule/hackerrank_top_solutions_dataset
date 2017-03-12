import scala.collection.mutable.ArrayBuffer
import java.util.PriorityQueue
import java.util.HashSet

object Solution {

    def main(args: Array[String]) {
        val input = readLine.split(" ").map(_.toInt)
        val graph = new Graph(input(0))
            
        for (i <- 0 until input(1)) {
            val edge = readLine.split(" ").map(_.toInt)
            graph.addEdge(edge(0) - 1, edge(1) - 1, edge(2))
        }
        
        println(mst(graph))
    }
    
    def mst(graph: Graph): Int = {
        val queue = new PriorityQueue[Edge]()
            
        for (v <- 0 until graph.V) {
            for (i <- 0 until graph.adj(v).size) {
                queue.offer(new Edge(v, graph.adj(v)(i), graph.weight(v)(i)))
            }
        }
        
        val cc = new CC(graph.V)
        var count = 0
            
        while (!queue.isEmpty) {
            val edge = queue.poll()
            if (!cc.connected(edge.v, edge.w)) {
                count += edge.weight
                cc.union(edge.v, edge.w)
            }
        }
            
        count
    }
}

class Graph(n: Int) {
    val adjacent = Array.fill[ArrayBuffer[Int]](n)(new ArrayBuffer())
    val weightList = Array.fill[ArrayBuffer[Int]](n)(new ArrayBuffer())
    
    def addEdge(v: Int, w:Int, weight: Int): Unit = {
        adjacent(v) += w
        adjacent(w) += v    
        
        weightList(v) += weight            
        weightList(w) += weight
    }
    
    def adj(v: Int): ArrayBuffer[Int] = adjacent(v)
        
    def weight(v: Int): ArrayBuffer[Int] = weightList(v)
    
    def V():Int = n
}

class Edge(val v: Int, val w: Int, val weight: Int) extends Comparable[Edge] {
    def compareTo(other: Edge): Int = weight.compareTo(other.weight)    
}

class CC(n: Int) {
    val cc = new Array[Int](n)
    val weight = Array.fill[Int](n)(1)
    for (i <- 1 until n) cc(i) = i
    
    def union(v: Int, w: Int): Unit = {
        val rootV = root(v)
        val rootW = root(w)
            
        if (weight(rootV) < weight(rootW)) {
            cc(rootV) = rootW
            weight(rootW) += weight(rootV)
        } else {
            cc(rootW) = rootV
            weight(rootV) += weight(rootW)
        }
    }
    
    def root(a: Int) = {
        var v = a
        while(v != cc(v)) {
            cc(v) = cc(cc(v))
            v = cc(v)
        }
        
        v
    }

    def connected(v: Int, w: Int): Boolean = root(v) == root(w)
}
