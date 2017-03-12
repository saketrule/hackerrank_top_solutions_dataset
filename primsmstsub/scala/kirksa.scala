import scala.collection.mutable

object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    val m = sc.nextInt()
    val emptyEdgesMap = (1 to n).map( (_, Set[(Int, Int)]())).toMap
    val edgesMap = (1 to m).map ( _ => (sc.nextInt(), sc.nextInt(), sc.nextInt())).foldLeft(emptyEdgesMap)(
      (soFar, tr) => {
        val (from, to, len) = tr
        soFar.updated(from, soFar(from) + ((to, len))).updated(to, soFar(to) + ((from, len)))
      }
    )
    val s = sc.nextInt()

    println(sol(edgesMap.map({ case (from, adj) => (from, adj.toList.sortBy(_._2))}), s, n))
  }

  def sol(edges : Map[Int, List[(Int, Int)]], start: Int, n:Int): Int = {
    var visited = Set[Int](start)
    val queue = mutable.PriorityQueue.empty[(Int, Int)](
      implicitly[Ordering[(Int, Int)]].reverse
    )
    queue.enqueue(edges(start).map(_.swap) :_*)
    var nodeToDistance = 0

    while (queue.nonEmpty) {
      val (len, node) = queue.dequeue()
      if (!visited.contains(node)) {
        visited = visited + node
        nodeToDistance += len
        queue.enqueue(edges(node).map( a => (a._2, a._1)) :_*)
      }
    }
    nodeToDistance
  }
}