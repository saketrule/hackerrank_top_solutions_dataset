import scala.collection.mutable
import scala.io.Source
    
object Solution {
class SimpleDisjointSet[T] {

  case class Node[T](var parent: T, var rank: Int)

  val lookup = mutable.HashMap[T, Node[T]]()

  def makeSet(in: T) = lookup += in -> Node(in, 0)

  def union(l: T, r: T): Unit = {
    val (lRep, rRep) = (find(l), find(r))
    if (lRep == rRep) return
    val (lInfo, rInfo) = (lookup(lRep), lookup(rRep))
    if (lInfo.rank > rInfo.rank)
      rInfo.parent = lRep
    else {
      lInfo.parent = rRep
      if (lInfo.rank == rInfo.rank)
        rInfo.rank += 1
    }
  }

  def find(in: T): T = {
    val inRep = lookup(in)
    if (!inRep.parent.equals(in))
      inRep.parent = find(inRep.parent)
    inRep.parent
  }
}
    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
          val lines = Source.fromInputStream(System.in).getLines()
  val nm = lines.next().split(" ").map(_.toInt)
  val edges = mutable.HashMap[(Int, Int), Int]()
  for (i <- 1 to nm(1)) {
    val edge = lines.next().split(" ").map(_.toInt)
    edges.put((edge(0), edge(1)), edge(2))
  }
  val sortedEdges = edges.toSeq.sortBy(_._2)
  val disjointSet = new SimpleDisjointSet[Int]()
  for (i <- 1 to nm(0)) disjointSet.makeSet(i)
  var (ans, flag) = (0, false)
  for (edge <- sortedEdges) {
    if (disjointSet.find(edge._1._1) != disjointSet.find(edge._1._2)) {
      disjointSet.union(edge._1._1, edge._1._2)
      ans += edge._2
    }
    if (disjointSet.lookup.size == nm(0)) flag = true
  }
  println(ans)
    }
}