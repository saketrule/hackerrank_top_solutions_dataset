import scala.collection.mutable

object Solution {

    def main(args: Array[String]) {
    val T = io.StdIn.readInt()
    for (_ <- 1 to T) {
      val line = io.StdIn.readLine().trim.split(" ").map(_.toInt)
      val y = io.StdIn.readLine().trim.split(" ").map(_.toInt).toVector
      val x = io.StdIn.readLine().trim.split(" ").map(_.toInt).toVector
      println(cuttingBoards(line(0), line(1), y, x))
    }
  }

  def cuttingBoards(M: Int, N: Int, y: Vector[Int], x: Vector[Int]): Long = {
    if (M != y.length + 1 || N != x.length + 1) return -1l

    var cost = BigInt(0)
    var (xCuts, yCuts) = (0l, 0l)

    implicit val ordering = Ordering[Int]
    val yQ = mutable.PriorityQueue() ++ y
    val xQ = mutable.PriorityQueue() ++ x

    while (yQ.nonEmpty || xQ.nonEmpty) {
      if ((yQ.nonEmpty && xQ.nonEmpty && yQ.head > xQ.head) || xQ.isEmpty) {
        cost = cost + (yQ.dequeue() * (xCuts + 1))
        yCuts += 1
      } else {
        cost = cost + (xQ.dequeue() * (yCuts + 1))
        xCuts += 1
      }
    }

    (cost % BigInt((Math.pow(10, 9) + 7).toLong)).toLong
  }
}