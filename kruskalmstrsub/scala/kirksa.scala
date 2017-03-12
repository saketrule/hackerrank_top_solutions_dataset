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
    def solHelper(visited: Set[Int], queue: mutable.PriorityQueue[(Int, Int, Int)], soFar: Int): Int = {
      if (queue.isEmpty) {
        soFar
      } else {
        val (len, node, another) = queue.dequeue()
        if (visited.contains(node)) {
          solHelper(visited, queue, soFar)
        } else {
          solHelper(visited + node, queue ++ edges(node).map( a => (a._2, a._1, node)), soFar + len)
        }
      }
    }
    val visited = Set[Int](start)
    val queue = mutable.PriorityQueue.empty[(Int, Int, Int)](
      new Ordering[(Int, Int, Int)] {
        override def compare(x: (Int, Int, Int), y: (Int, Int, Int)): Int = if (x._1 > y._1) 1 else if (x._1 < y._1) -1 else
          implicitly[Ordering[Int]].compare(x._1 + x._2 + x._3, y._1 + y._2 + y._3)
      }.reverse
    )
    queue.enqueue(edges(start).map(a => (a._2, a._1, start)) :_*)

    solHelper(visited, queue, 0)
  }
}