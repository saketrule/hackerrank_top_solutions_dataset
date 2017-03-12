object Solution {

    def main(args: Array[String]) {
      val iter = io.Source.stdin.getLines
      val (n, m) = readPair(iter.next())
      val edges = iter.take(m).map(readPair).toSeq
      val x = xx(edges)
      println(x)
    }
  
    def xx(edges: Seq[(Int, Int)], index: Int = 0): Int = {
      if (index >= edges.size) {
        0
      } else {
        val removedEdge = edges(index)
        val set = collection.mutable.Set(removedEdge._1)
        val edges0 = edges.filterNot(_ == removedEdge)
        var count = 0
        do {
          count = set.size
          edges0.foreach {
            case (a, b) if set.contains(a) =>
              set += b
            case (a, b) if set.contains(b) =>
              set += a
            case _ =>
          }
        } while(count != set.size)
        if (set.size % 2 == 0) {
          val (edges1, edges2) = edges.filterNot(_ == removedEdge).partition {
            case (a, b) => set.contains(a) || set.contains(b)
          }
          1 + xx(edges1) + xx(edges2)
        } else {
          xx(edges, index + 1)
        }
      }
    }
  
    def readPair(s: String) = {
      val parts = s.split(" ")
      (parts(0).toInt, parts(1).toInt)
    }
}