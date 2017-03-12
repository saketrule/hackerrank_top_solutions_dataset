import scala.collection.mutable

object Solution {

    case class Edge(v: Int, w: Int, weight: Int) {
        def other(x: Int): Int =
            if (x == v) w
            else if (x == w) v
            else throw new IllegalArgumentException()
    }
    
    implicit val edgeReverseOrdering = new Ordering[Edge] {
        def compare(e1: Edge, e2: Edge): Int =
            e1.weight - e2.weight
    }.reverse    
    
    class Graph(val v: Int) {
        private val adjs: IndexedSeq[mutable.Buffer[Edge]] = IndexedSeq.fill(v)(mutable.Buffer.empty)
            
        def add(v: Int, w: Int, weight: Int): Unit = {
            adjs(v) += Edge(v, w, weight)
            adjs(w) += Edge(v, w, weight)
        }
        
        def adj(v: Int): Seq[Edge] = adjs(v)
    }
        
    class Prim(g: Graph, s: Int) {
        private val marked: mutable.IndexedSeq[Boolean] = mutable.IndexedSeq.fill(g.v)(false)
        private val pq: mutable.PriorityQueue[Edge] = mutable.PriorityQueue.empty[Edge]
        private val t: mutable.Buffer[Edge] = mutable.Buffer.empty[Edge]
        visit(s)
        while (pq.nonEmpty) {
            val e = pq.dequeue
            if(!marked(e.v) || !marked(e.w)) {
                t += e
                if (!marked(e.v)) visit(e.v)
                if (!marked(e.w)) visit(e.w)
            }
        }
            
        private def visit(u: Int) = {
            marked(u) = true
            for (e <- g.adj(u))
                if (!marked(e.other(u)))
                    pq.enqueue(e)
        }
        
        def mst: Seq[Edge] = t
    }
    
    def main(args: Array[String]) {
        val sc = new java.util.Scanner(System.in)
        val g = new Graph(sc.nextInt())
        val m = sc.nextInt()        
        Seq.fill(m) {
            g.add(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt())
        }    
        val prim = new Prim(g, sc.nextInt() - 1)
        println(prim.mst.foldLeft(0)(_ + _.weight))
    }
}