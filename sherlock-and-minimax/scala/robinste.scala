object Solution {
  import scala.io.StdIn._
  import scala.math._
  def main(args: Array[String]) {
    val N = readInt()
    val A = io.StdIn.readLine().split(" ", N).toSeq.map(_.toInt).sorted
    val Array(p, q) = io.StdIn.readLine().split(" ", N).map(_.toInt)
    println(solve(A, p, q))
  }
  def solve(A: Seq[Int], p: Int, q: Int): Any = {
    val M = 1000000001
    val B = -M +: A :+ M
    val l = B.lastIndexWhere(_ <= p)
    val h = B.indexWhere(_ >= q)
    val C = for (i <- l until h) yield {
      val m = max(p, min(q, (B(i) + B(i + 1)) / 2))
      val d = min(m - B(i), B(i + 1) - m)
      m -> d
    }
    C.maxBy(_._2)._1
  }
}