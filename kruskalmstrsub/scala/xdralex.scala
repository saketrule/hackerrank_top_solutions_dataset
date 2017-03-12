import java.io.FileInputStream
import java.util
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._
import scala.collection.JavaConverters._
import java.{lang => jl}

object Solution {

  class DisjointSet(n: Int) {
    val a = new Array[Int](n)
    val s = new util.HashMap[Int, util.LinkedList[Int]]()

    for (i <- 0 until n) {
      a(i) = i

      val ll = new util.LinkedList[Int]()
      ll.addLast(i)
      s.put(i, ll)
    }

    def size(): Int = s.size()

    def find(x: Int): Int = {
      a(x)
    }

    def merge(sx: Int, sy: Int) {
      val (big, small) = if (s.get(sx).size() >= s.get(sy).size) { (sx, sy) } else { (sy, sx) }
      val iter = s.get(small).iterator()
      while (iter.hasNext) {
        val i = iter.next()
        a(i) = big
      }

      s.get(big).addAll(s.get(small))
      s.remove(small)
    }

    def dump() = {
      "a = [%s]; s = <%s>".format(a.mkString(", "), s.asScala.map {case (x, ll) => "%s - [%s]".format(x, ll.asScala.mkString(", "))}.mkString("; "))
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val M = sc.nextInt()

    val g = new util.HashMap[(Int, Int), jl.Long]()

    def updateG(x: Int, y: Int, r: Long) {
      val v = Option(g.get((x, y))).getOrElse(jl.Long.MAX_VALUE).asInstanceOf[Long]
      if (r < v) {
        g.put((x, y), r)
      }
    }

    for (_ <- 0 until M) {
      val x = sc.nextInt() - 1
      val y = sc.nextInt() - 1
      val r = sc.nextInt()

      updateG(x, y, r)
    }

    val edges = g.asScala.toArray.sortBy(x => x._2)

//    println("Edges: %s".format(edges.map(x => "%s-%s#%s".format(x._1._1, x._1._2, x._2)).mkString(", ")))

    var weight: Long = 0L
    val set = new DisjointSet(N)
    breakable {
      for (((u, v), r) <- edges) {
        if (set.size() == 1) {
          break
        }

//        println("Set: %s".format(set.dump()))
//        print("U=%s, V=%s, R=%s => ".format(u, v, r))

        val s_u = set.find(u)
        val s_v = set.find(v)
        if (s_u != s_v) {
//          println("Merging: %s & %s".format(s_u, s_v))

          set.merge(s_u, s_v)
          weight += r
        }
      }
    }

    println(weight)
  }
}
