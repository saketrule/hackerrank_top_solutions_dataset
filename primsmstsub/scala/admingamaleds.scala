import java.io.{BufferedReader, InputStreamReader, PrintStream}

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

object Solution extends App {

  def execute(in: BufferedReader, out: PrintStream) = {
    val ins = in.readLine().split(" ")
    val nodes = ins(0).toInt
    val edgesQ = ins(1).toInt
    val values = Array.ofDim[Int](edgesQ, 3)
    val rnd = new Random()
    (0 to edgesQ - 1).foreach{c =>
      val e = in.readLine().split(" ")
      val x = e(0).toInt
      val y = e(1).toInt
      val w = e(2).toInt
      values.update(c, Array(x, y, w))
    }
    val sortedValues = values.sortBy(_.apply(2))
    val start = in.readLine().toInt

    @tailrec
    def solve(graphNodes: Set[Int], remainingNodes: Set[Int], w: BigInt, remainingEdges: Array[Array[Int]]): BigInt = {
      if(remainingNodes.isEmpty) {
        w
      } else {
        val nextEdge = remainingEdges.find{a =>
          (remainingNodes.contains(a(0)) && graphNodes.contains(a(1))) || (remainingNodes.contains(a(1)) && graphNodes.contains(a(0)))
        }.get
        val nextNode = if(remainingNodes.contains(nextEdge(0))) nextEdge(0) else nextEdge(1)
        val newGraphNodes = graphNodes + nextNode
        val otherEdges = remainingEdges.filter(a => !newGraphNodes.contains(a(0)) || !newGraphNodes.contains(a(1)))
        solve(newGraphNodes, remainingNodes.filterNot(nextNode.equals), w + nextEdge(2), otherEdges)
      }
    }

    val r = solve(Set(start), (1 to nodes).filterNot(start.equals).toSet, 0, sortedValues)
    println(r)
  }

  execute(new BufferedReader(new InputStreamReader(System.in)), System.out)
}
