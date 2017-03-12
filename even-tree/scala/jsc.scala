import collection.mutable.ArrayBuffer

class Node(val idx: Int) {
  val neighbors = ArrayBuffer[Node]()
  var descendantCount = 0
  def addNeighbor(n: Node) { neighbors += n }
  def DFS(visited: Array[Boolean])(f: (Node, ArrayBuffer[Node]) => Unit) {
    visited(idx) = true
    val children = neighbors.filterNot(n => visited(n.idx))
    for (c <- children) c.DFS(visited)(f)
    f(this, children)
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    val Array(n, m) = readLine().split(" ").map(_.toInt)
    val nodes = new Array[Node](n + 1)
    for (i <- 1 to n) nodes(i) = new Node(i)
    for (_ <- 0 until m) {
      val Array(a, b) = readLine().split(" ").map(i => nodes(i.toInt))
      a.addNeighbor(b)
      b.addNeighbor(a)
    }

    var ans = 0
    val root = nodes(1)
    root.DFS(new Array[Boolean](n + 1)) { (node, children) =>
      if (node != root) {
        node.descendantCount = children.foldLeft(children.length) { (sum, c) => sum + c.descendantCount }
        if ((node.descendantCount & 1) == 1) ans += 1
      }
    }
    println(ans)
  }
}
