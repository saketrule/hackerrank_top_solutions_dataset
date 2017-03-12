import scala.collection.mutable
object Solution {
type Weight = Int
  type Graph = scala.collection.mutable.Map[Node, List[(Node, Weight)]] // Adjacency List
  type Node = String

  def main(args: Array[String]): Unit = {
    val in = io.Source.fromInputStream(System.in).getLines()
    val (nNodes, nEdges) = {
      val toks = in.next() split " "
      (toks(0).toInt, toks(1).toInt)
    }

    //init
    val graph: Graph = mutable.Map[Node, List[(Node, Weight)]]()
    (1 to nNodes) foreach (e => graph(e.toString) = List.empty[(Node, Weight)])

    var start: Option[Node] = None
    while (in.nonEmpty){
      val toks = in.next() split " "
      if(toks.length == 3) {
        val u = toks(0)
        val v = toks(1)
        val weight = toks(2).toInt
        //undirected edges. add to both
        graph(u) = (v, weight) :: graph(u)
        graph(v) = (u, weight) :: graph(v)
      }else {
        start = Some(toks(0))
      }
    }
    println(prims(graph, start.get))
  }

  //Multiple edges.
  def prims(graph: Graph, start: Node): Long = {
    val nodes = graph.keys.toList
    var pq = mutable.PriorityQueue[(Node, Weight)](nodes.map(x => (x, Int.MaxValue)): _*)(Ordering.by((e: (Node, Weight)) => e._2).reverse)
    def updatePQ(node: Node, weight: Weight) = if(pq.exists(_._1 == node)){
      pq = pq.filterNot(_._1 == node)
      pq.enqueue((node, weight))
    }
    def dist(node: Node): Weight = {
      val weights = pq.filter(_._1 == node).map(_._2)
      if(weights.isEmpty) Int.MaxValue else weights.min
    }

    //init
    var sum = 0
    updatePQ(start, 0)
    while(pq.nonEmpty){
      val (u, c) = pq.dequeue()
      sum = sum + c
      //update immediate neighbors
      val neighbors = graph(u)
      for ((v, l) <- neighbors){
        if(dist(v) > l){
          updatePQ(v, l)
        }
      }
    }
    sum
  }
}