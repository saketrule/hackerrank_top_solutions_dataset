
object Solution {
  private def readLine: String = {
    scala.io.StdIn.readLine()
  }

  def main(args:Array[String]) = {
    val arr = readLine.trim.split(' ').map(s => s.toInt)
    val N = arr(0)
    val M = arr(1)

    val rank = Array.ofDim[Int](N + 1)
    val fuparent = Array.ofDim[Int](N + 1)

    def makeSet(x:Int): Unit = {
      fuparent(x) = x
      rank(x) = 0
    }

    def union(x:Int, y:Int) = {
      val xRoot = find(x)
      val yRoot = find(y)

      if (rank(xRoot) > rank(yRoot)) {
        fuparent(yRoot) = xRoot
      } else if (rank(xRoot) < rank(yRoot)) {
        fuparent(xRoot) = yRoot
      } else if (xRoot != yRoot) {
        fuparent(yRoot) = xRoot
        rank(xRoot) += 1
      }
    }

    def find(x:Int) : Int = {
      if (fuparent(x) == x) x
      else {
        fuparent(x) = find(fuparent(x))
        fuparent(x)
      }
    }

    (1 to N).foreach(i => makeSet(i))

    val edges = Array.ofDim[(Int,Int,Int)](M)

    for (i <- 1 to M) {
      val tmp = readLine.trim.split(' ').map(s => s.toInt)

      val x = tmp(0)
      val y = tmp(1)
      val r = tmp(2)

      edges(i - 1) = (x, y, r)
    }

    val start = readLine.trim.toInt

    val sortedEdges = edges.sortWith((tuple0: (Int, Int, Int), tuple1: (Int, Int, Int)) => {
          if (tuple0._3 < tuple1._3) true
          else if (tuple0._3 == tuple1._3) {
            val s0 = tuple0._1 + tuple0._2 + tuple0._3
            val s1 = tuple1._1 + tuple1._2 + tuple1._3

            if (s0 < s1) true else false
          } else false
       })

    var total = 0L

    sortedEdges.foreach(p => {
      val (x, y, r) = p
      if (find(x) != find(y)) {
        union(x, y)
        total += r
      }
    })

    println(total)
  }
}
