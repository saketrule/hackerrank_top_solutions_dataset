object Solution {
  import scala.collection.mutable
  
  class DisjointSet(val x: Int) {
    var parent = this
    var rank = 0

    def root: DisjointSet = {
      if (this == parent) this
      else {
        parent = parent.root
        parent
      }
    }

    def union(that: DisjointSet) = {
      val thisRoot = this.root
      val thatRoot = that.root

      if (thisRoot.rank < thatRoot.rank) thisRoot.parent = thatRoot
      else if (thisRoot.rank > thatRoot.rank) thatRoot.parent = thisRoot
      else if (thisRoot != thatRoot) {
        thatRoot.parent = thisRoot
        thisRoot.rank += 1
      }
    }
  }

  val input = new java.util.Scanner(System.in)

  case class Edge(i: Int, j: Int, weight: Int) extends Ordered[Edge] {
    def compare(that: Edge): Int = {
      val result = weight.compare(that.weight)
      if (result != 0) result
      else (i + j + weight).compare(that.i + that.j + that.weight)
    }
  }

  def kruskal() = {
    val n = input.nextInt()
    val m = input.nextInt()

    val edges = mutable.Queue.fill(m)(Edge(input.nextInt() - 1, input.nextInt() - 1, input.nextInt())).sorted

    val graphSets = Array((1 to n).map(new DisjointSet(_)): _*)

    var totalWeight = 0
    while ( edges.nonEmpty) {
      val Edge(i, j, weight) = edges.dequeue()
      if (graphSets(i).root != graphSets(j).root) {
        totalWeight += weight
        graphSets(i).union(graphSets(j))
      }
    }

    println(totalWeight)
  }

  def main (args: Array[String]): Unit = {
    kruskal()
  }
}