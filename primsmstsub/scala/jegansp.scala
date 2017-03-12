import scala.collection.mutable
import scala.io.StdIn

object Solution {

    def main(args: Array[String]) {
      val input = StdIn.readLine().split(" ")
      val nodes = input(0).toInt
      val edges = input(1).toInt
      val graph = mutable.Map[Int, List[GEdge]]()
      for (i <- 0 until edges) {
        val line = StdIn.readLine().split(" ")
        val from = line(0).toInt
        val to = line(1).toInt
        graph += (from -> (GEdge(to, line(2).toInt) :: graph.getOrElse(from, List())))
        graph += (to -> (GEdge(from, line(2).toInt) :: graph.getOrElse(to, List())))
      }
      val s = StdIn.readInt()

      implicit object EdgeOrdering extends Ordering[GEdge] {
        override def compare(x: GEdge, y: GEdge): Int = y.weight compare x.weight
      }

      val ord = EdgeOrdering

      val minHeap = scala.collection.mutable.PriorityQueue.empty(ord)
        graph.get(s).foreach(_.foreach(e => minHeap += e))

      val visited = mutable.Set[Int]()
      visited += s

      var sum = 0
      def mst: Int = {
        while (minHeap.nonEmpty) {
          if (!visited.contains(minHeap.head.to)) {
            val min = minHeap.dequeue()
            visited += min.to
            val newEdges = graph.getOrElse(min.to, List()).filter(e => !visited.contains(e.to))
            newEdges.foreach(e => minHeap += e)
            sum = sum + min.weight
          } else {
            val deq = minHeap.dequeue()
          }
        }
        sum
      }
      println(mst)
    }
}

case class GNode(elem: Int)

case class GEdge(to: Int, weight: Int)