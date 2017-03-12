object Solution extends App {
  val in = scala.io.Source.stdin.getLines()
  val Array(n, m) = in.next().split(" ").map(_.toInt)
  val queue = scala.collection.mutable.PriorityQueue.empty[(Int, Int)] //distance, nodeB
  val data = Array.fill(n){List.empty[(Int, Int)]} //distance, nodeB
  (1 to m).foreach { _ =>
    val Array(nodeA, nodeB, distance) = in.next().split(" ").map(_.toInt)
    data(nodeA - 1) ::= (distance, nodeB - 1)
    data(nodeB - 1) ::= (distance, nodeA - 1)
  }
  val s = in.next().toInt - 1 // no one cares
  var distance = 0
  val joined = scala.collection.mutable.Set.empty[Int]
  queue.enqueue((0, s))
  while (joined.size != n) {
    val (ndistance, node) = queue.dequeue()
    if (!joined.contains(node)) {
      joined += node
      distance += -ndistance
      data(node).foreach {
        case (d, nnode) if joined.contains(nnode) =>
        case (d, nnode) => queue.enqueue((-d, nnode))
      }
    }
  }
  println(distance)
}
