import scala.collection.mutable.ArrayBuffer
object Solution {

    def main(args: Array[String]) {
      val graph = new Graph()
    val tokens = readLine().split(" ").map(_.trim().toInt)

    1.to(tokens(0)).foreach(i => {
      graph.addNode()
    })

    1.to(tokens(1)).foreach(i => {
      val edgeTokens = readLine().split(" ").map(_.trim().toInt)

      if(edgeTokens.size < 3) {
        println()
        println(edgeTokens.mkString(" "))
      }

      graph.addEdge(edgeTokens(0), edgeTokens(1), edgeTokens(2))
    })

    val query = readLine.trim().toInt

    // PRINT THE DISTANCES
    println(graph.minimumSpanningTreeWeight(query))
    }
}


class Graph {
  var currNodeId = 1
  var nodes = collection.mutable.Map[Int, Node]()
  var distances = collection.mutable.Map[(Int, Int), Int]()

  def addNode() = {
    nodes(currNodeId) = new Node(currNodeId)
    currNodeId = currNodeId + 1
  }

  def addEdge(firstNodeId: Int, secondNodeId: Int, weight: Int = 1) = {
    if(!nodes.contains(firstNodeId) || !nodes.contains(secondNodeId)) {
      throw new Exception(s"No node with ID $firstNodeId or $secondNodeId.")
    }

    nodes(firstNodeId).addEdge(nodes(secondNodeId), weight)
    nodes(secondNodeId).addEdge(nodes(firstNodeId), weight)
  }
    
  /**
    * Computes a minimum spanning tree weight given a starting node via Prim's Algorithm.
    *
    * @return Graph
    */
  def minimumSpanningTreeWeight(startNodeId: Int): Int = {
    object MinWeightOrdering extends Ordering[Edge] {
      def compare(x:Edge, y:Edge) = y.weight compare x.weight
    }

    var currNodeId = startNodeId
    var weight = 0
    var seenNodes = ArrayBuffer[Int](startNodeId)
    var availableEdges = scala.collection.mutable.PriorityQueue.empty(MinWeightOrdering)

    nodes(currNodeId).edges.foreach(edge => availableEdges.enqueue(edge))

    while(availableEdges.nonEmpty) {
      var nextEdge: Edge = availableEdges.dequeue()

      currNodeId = nextEdge.node.id
      weight = weight + nextEdge.weight
      seenNodes.append(currNodeId)

      nodes(currNodeId).edges.filter(edge => !seenNodes.contains(edge.node.id)).foreach(edge => availableEdges.enqueue(edge))

      while(availableEdges.nonEmpty && seenNodes.contains(availableEdges.head.node.id)) {
        availableEdges.dequeue()
      }
    }

    weight
  }
}

case class Node(id: Int, edges: ArrayBuffer[Edge] =  ArrayBuffer[Edge]()) {
  override def toString: String = {
    s"Node(id: $id, edges: (${edges.map(_.node.id).mkString(",")})"
  }

  override def hashCode: Int = {
    id
  }

  def addEdge(node: Node, weight: Int = 1): Unit = {
    edges.append(new Edge(node, weight))
  }
}

case class Edge(node: Node, weight: Int = 1)