  object Solution {
    def main(args: Array[String]) {
      val sc = new java.util.Scanner(System.in)

      val N = sc.nextInt
      val x = (for (i <- 1 to N) yield {
        sc.nextInt
      }).sorted

      val P = sc.nextInt
      val Q = sc.nextInt

//      println(x)

      val x2 = P +: Q +: x.zip(x.tail).flatMap { case (a, b) =>
        Set(a + (b - a) / 2, a + (b + 1 - a) / 2)
      }
      val xx = x2.filter(t => P <= t && t <= Q).sorted

//      println(xx)

      val xxx = xx.map { t => (x.map(tt => math.abs(tt - t)).min, -t) }
//      println(xxx)

      val res = -xxx.max._2
      println(res)
    }
  }
