object Solution {
  def main(args: Array[String]) {
    val t = readInt()

    for (tc <- 1 to t) {
      val Array(m, n) = readLine().split("\\s+").map(_.toLong)
      val y = readLine().split("\\s+").map(_.toLong)
      val x = readLine().split("\\s+").map(_.toLong)
      val list: List[(Long, Int)] = (x.map((_, 0)) ++ y.map((_, 1))).sorted.reverse.toList
      println(solve(list, 1, 1, 0))
    }
  }

  def solve(list: List[(Long, Int)], xseg: Long, yseg: Long, ans: Long): Long = {
    list match {
      case (cost, dir) :: rest =>
        solve(rest, xseg + 1 - dir, yseg + dir, (ans + cost * (xseg * dir + yseg * (1 - dir))) % 1000000007)
      case _ =>
        ans
    }
  }
}
