object Solution {

  import scala.io.StdIn._

  case class Input(n: Int, arr: Array[Long], p: Long, q: Long)

  def main(args: Array[String]) {
    println(calculate(readStdin))
  }

  def readStdin: Input = {
    val n = readLine().trim.toInt
    val arr = readLine().trim.split(" +").map(_.toLong)
    val (p, q) = readLine().trim.split(" +").take(2).map(_.toLong) match {
      case Array(a, b) => (a, b)
    }
    Input(n, arr, p, q)
  }

  def readFile: Input = {
    val lines = scala.io.Source.fromFile(getClass.getResource("/sharlock_minmax_testcase0.txt").getPath).getLines()
    val n = lines.next().trim.toInt
    val arr = lines.next().trim.split(" +").map(_.toLong)
    val (p, q) = lines.next().trim.split(" +").take(2).map(_.toLong) match {
      case Array(a, b) => (a, b)
    }
    Input(n, arr, p, q)
  }

  def calculate(input: Input): Long =
    input match {
      case Input(n, arr, p, q) =>
        val sorted = arr.sorted
        var max = (0L, 0L)
        val left = if (p < sorted(0)) (sorted(0) - p, p) else (0L, p)
        val right = if (q > sorted.last) (q - sorted.last, q) else (0L, q)
        val kvs = sorted.sliding(2).map { case Array(a, b) => middle(a, b, p, q) }
        (Iterator(left) ++ kvs ++ Iterator(right)).toList.foreach { case tp =>
          max = if (max._1 < tp._1) tp else max
        }
        max._2
    }


  // returns tuple (distance, position)
  def middle(a: Long, b: Long, p: Long, q: Long): (Long, Long) = {
    val x = math.min(a,b)
    val y = math.max(a,b)
    if (x > q || y < p) (0L, 0L)
    else {
      val mid = math.abs((y + x) / 2)
      if (mid < p)
        (math.abs(y - p), p)
      else if (mid > q) (math.abs(q - x), q)
      else (math.abs((y - x) / 2), mid)
    }
  }
}