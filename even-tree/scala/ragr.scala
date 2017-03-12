object Solution {

    class Graph(_nodeCount: Int) {
    val nodeCount = _nodeCount
    val edges = Array.ofDim[Boolean](nodeCount, nodeCount)

    def addEdge(node1:Int, node2:Int) = {
      edges(node1)(node2) = true
      edges(node2)(node1) = true
    }

    def getAdjacentNodes(node:Int) = {
      edges(node).zipWithIndex.filter(_._1==true).map(_._2).toList
    }

  }

  class Tree(_nodeCount: Int) {
    val nodeCount = _nodeCount
    val edges = Array.ofDim[Boolean](nodeCount, nodeCount)

    def addEdge(node1:Int, node2:Int) = {
      edges(node1)(node2) = true
    }

    def getChildren(node:Int) = {
      edges(node).zipWithIndex.filter(_._1==true).map(_._2).toList
    }

  }

  class GraphToTreeConverter(graph: Graph) {
    val tree = new Tree(graph.nodeCount)
    val visited = new Array[Boolean](graph.nodeCount)

    def dfs(node: Int) {
      visited(node) = true
      for (adj <- graph.getAdjacentNodes(node)) {
        if (!visited(adj)) {
          tree.addEdge(node, adj)
          dfs(adj)
        }
      }
    }

    def convert = {
      dfs(0)
      tree
    }

  }

  def calculateNodeCounts(tree:Tree, node:Int) : (Int,Int) = {
    var nodeCount = 1
    var edgeCount = 0
    for (child <- tree.getChildren(node)) {
      val (childNodeCount, childEdgeCount) = calculateNodeCounts(tree, child)
      nodeCount += childNodeCount
      edgeCount += childEdgeCount
      if (childNodeCount%2 == 0) {
        edgeCount += 1
      }
    }
    (nodeCount, edgeCount)
  }  
    
    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
            val input = io.Source.stdin.getLines
    val Array(numNodes, numEdges) = input.next.split(" ").map(_.toInt)
    val graph = new Graph(numNodes)

    for (line <- input.take(numEdges)) {
      val Array(from, to) = line.split(" ").map(_.toInt - 1)
      graph.addEdge(from, to)
    }

    val tree = new GraphToTreeConverter(graph).convert

    println(calculateNodeCounts(tree, 0)._2)
    }
}