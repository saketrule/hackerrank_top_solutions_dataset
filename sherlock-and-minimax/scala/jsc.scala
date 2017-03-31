import scala.io.StdIn._
import scala.collection.mutable.ArrayBuffer

object Solution {
  def main(args: Array[String]): Unit = {
    val n = readInt
    val a = readLine.split(" ").map(_.toInt).sorted
    val Array(p, q) = readLine.split(" ").map(_.toInt)
    val midpoints = ArrayBuffer[(Int, Int)]() // (midpoint, distance)
    for (Array(a, b) <- a.sliding(2)) {
      val dist = (b - a) / 2
      if ((b - a) % 2 == 0) midpoints += a + dist -> dist
      else {
        midpoints += a + dist -> dist
        midpoints += b - dist -> dist
      }
    }
    val midpointsInRange = midpoints.filter(m => p <= m._1 && m._1 <= q)

    // add in endpoints
    val pi = a.indexWhere(_ > p)
    if (pi > 0) midpointsInRange.prepend(p -> (a(pi) - p).min(p - a(pi - 1)))
    else midpointsInRange.prepend(p -> (a(pi) - p))

    val qi = a.lastIndexWhere(_ < q)
    if (qi < a.length - 1) midpointsInRange += q -> (q - a(qi)).min(a(qi + 1) - q)
    else midpointsInRange += q -> (q - a(qi))

    val m = midpointsInRange.maxBy(n => n._2)
    println(m._1)
  }
}
