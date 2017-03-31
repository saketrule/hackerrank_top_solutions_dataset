import java.util.Scanner


object Solution {

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)

    val n = sc.nextInt()
    val ns = (1 to n)
      .map(_ => sc.nextLong())
      .sorted

    val p = sc.nextLong()
    val q = sc.nextLong()

    if (p >= ns.last) {
      println(q)
    } else if (q <= ns.head) {
      println(p)
    } else {
      var m: Long = ns(0)
      var minDiff: Long = 0

      if (p <= ns.head) {
        m = p
        minDiff = ns.head - p
      }

      for (i <- 0 until n - 1) {
        val c = Math.max(p, Math.min((ns(i) + ns(i + 1)) / 2, q))

        if (p <= c && c <= q && Math.min(c - ns(i), ns(i + 1) - c) > minDiff) {
          m = c
          minDiff = c - ns(i)
        }
      }

      if (q >= ns.last && q - ns.last > minDiff) {
        m = q
      }

      println(m)
    }
  }
}