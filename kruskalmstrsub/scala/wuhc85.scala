import scala.annotation.tailrec
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
            if (e1.weight == e2.weight) e2.v + e2.w - e1.v - e1.w
            else e2.weight - e1.weight
    }

    class Kruskal(n: Int) {
        private val pq: mutable.PriorityQueue[Edge] = mutable.PriorityQueue.empty[Edge]
        
        def add(v: Int, w: Int, weight: Int): Unit =
            pq.enqueue(Edge(v, w, weight))
                    
        def mst: Seq[Edge] = {
            val uf = new UnionFind(n)
            val b = mutable.Buffer.empty[Edge]            
            while (b.size < n - 1) {
                val e = pq.dequeue
                if (!uf.connected(e.v, e.w)) {
                    uf.union(e.v, e.w)
                    b += e                    
                }    
            }
            b
        }
            
    }
        
    class UnionFind(n: Int) {
        private val ranks: mutable.IndexedSeq[Int] = mutable.IndexedSeq.fill(n)(1)
        private val parents: mutable.IndexedSeq[Int] = (0 to n - 1).to[mutable.IndexedSeq]
        private val components: mutable.IndexedSeq[Int] = mutable.IndexedSeq.fill(n)(0)
                    
        def union(u: Int, v: Int): Unit = {
            val ru = root(u)
            val rv = root(v)
            if (u != v) {
                if (ranks(u) < ranks(v))
                    parents(ru) = rv
                else if (ranks(u) < ranks(v))
                    parents(rv) = ru
                else {
                    parents(ru) = rv
                    ranks(rv) += 1
                }
            }
        }
        
        def connected(u: Int, v: Int): Boolean =
            root(u) == root(v)
        
        @tailrec private def root(u: Int): Int = 
            if (parents(u) == u) u
            else root(parents(u))
    }

    def main(args: Array[String]) {
        val sc = new java.util.Scanner(System.in)
        val kruskal = new Kruskal(sc.nextInt())
        val m = sc.nextInt()        
        Seq.fill(m) {
            kruskal.add(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt())
        }        
        println(kruskal.mst.foldLeft(0)(_ + _.weight))
    }
}