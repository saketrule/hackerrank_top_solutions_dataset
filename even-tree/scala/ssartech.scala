import scala.collection.mutable


object Solution {

  def dfs(cur: Int, connections: mutable.HashMap[Int, mutable.Set[Int]], parent: Int): (Int, Int) = {
    val (even, sum) = connections(cur).toList.filter(_!=parent).map(dfs(_,connections, cur)).foldLeft((0,1))((b,a) =>  (b._1 + a._1, b._2 + a._2))
    (even + (sum+1)%2, sum)
  }

  def main(args: Array[String]) {
    val n :: m :: _ = readLine().split(" ").map(_.toInt).toList
    val connections = new mutable.HashMap[Int, mutable.Set[Int]] with mutable.MultiMap[Int, Int]
    (1 to m).foreach { i =>
      val a :: b :: _ = readLine().split(" ").map(_.toInt).toList
      connections.addBinding(a,b)
      connections.addBinding(b,a)
    }

    val(even, sum) = dfs(1,connections, -1)
    println(even-1)
  }
}