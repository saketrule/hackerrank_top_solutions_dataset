import io.StdIn._

object Solution {
  def main(args: Array[String]): Unit = {
    val n = readInt()
    val sticks = readLine().split(" ").map(_.toInt).sorted
    var lastLength = -1
    for (i <- 0 until n; x = sticks(i)) {
      if (x != lastLength) {
        lastLength = x
        println(n - i)
      }
    }
  }
}