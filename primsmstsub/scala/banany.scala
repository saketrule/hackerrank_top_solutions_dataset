import scala.collection.mutable
import scala.io.StdIn._

object Solution {
  class FindUnion(val size: Int) {
    private val father: Array[Int] = (0 until size).toArray
    private val rank: Array[Int] = Array.fill(size)(0)

    def find(a: Int): Int = {
      if (father(a) != a) father(a) = find(father(a))
      father(a)
    }

    def areDisjoint(a: Int, b: Int): Boolean = find(a) != find(b)

    def union(a: Int, b: Int): Unit = {
      val (fa, fb) = (find(a), find(b))
      if (fa != fb) {
        if (rank(fa) >= rank(fb)) {
          father(fb) = fa
          rank(fa) += rank(fb)
        } else {
          father(fa) = fb
          rank(fb) += rank(fa)
        }
      }
    }
  }

  class Graph(val size: Int) {

    class Edge(val dest: Int, val value: Int) {}

    private val vertices: List[Int] = (0 until size).toList
    private val neighbours: Array[List[Edge]] = Array.fill(size)(List.empty)

    def addEdge(a: Int, b: Int, value: Int = 0): Unit = neighbours(a) = new Edge(b, value) :: neighbours(a)

    def addUnDirEdge(a: Int, b: Int, value: Int = 0): Unit = { addEdge(a, b, value); addEdge(b, a, value) }

    def getEdges(v: Int): List[Edge] = neighbours(v)

    def dfs(now: Int, visited: Array[Boolean]): Unit = {
      visited(now) = true
      getEdges(now) foreach { x: Edge => if (!visited(x.dest)) dfs(x.dest, visited) }
    }

    def dfs(x: Int): Unit = dfs(x, Array.fill(size)(false))

    def connectedComponentsAm(): Int = {
      val visited: Array[Boolean] = Array.fill(size)(false)

      vertices.foldLeft(0) {
        (sol, x) =>
          if (!visited(x)) {
            dfs(x, visited)
            sol + 1
          }
          else sol
      }
    }

    def bfs(st: Int): Unit = {
      val visited: Array[Boolean] = Array.fill(size)(false)
      val que = mutable.Queue.empty[(Int, Int)]
      que += ((st, 0))

      while (que.nonEmpty) {
        val (nowV, nowDepth) = que.dequeue()
        for (n <- getEdges(nowV); if !visited(n.dest)) {
          visited(n.dest) = true
          que += ((n.dest, nowDepth + 1))
        }
      }
    }

    def dijkstra(st: Int): Array[Int] = {
      val dist = Array.fill(size)(-1)
      var next = mutable.SortedSet.empty[(Int, Int)]
      dist(st) = 0
      next += ((dist(st), st))

      while (next.nonEmpty) {
        val (_, nowV) = next.min
        next -= next.min
        for (n <- getEdges(nowV); if dist(n.dest) == -1 || (n.value + dist(nowV) < dist(n.dest))) {
          next -= ((dist(n.dest), n.dest))
          dist(n.dest) = n.value + dist(nowV)
          next += ((dist(n.dest), n.dest))
        }
      }

      dist
    }

    def getAllEdges: List[(Int, Int, Int)] = vertices flatMap { v => getEdges(v) map { e => (e.value, v, e.dest) } }

    def kruskal(): Int = {
      val sets = new FindUnion(size)

      getAllEdges.sorted.foldLeft(0) {
        case (res, (value, st, ed)) =>
          if (sets.areDisjoint(st, ed)) {
            sets.union(st, ed)
            res + value
          }
          else res
      }
    }

    def prim(st: Int): Int = {
      val next = mutable.SortedSet.empty[(Int, Int)]
      var res = 0
      val visited: Array[Boolean] = Array.fill(size)(false)

      def addEdgesToSet(v: Int) = getEdges(v) foreach { e => if (!visited(e.dest)) { next += ((e.value, e.dest)) } }

      addEdgesToSet(st)
      visited(st) = true

      while (next.nonEmpty) {
        val (value, ed) = next.min
        next -= next.min
        if (!visited(ed)) {
          addEdgesToSet(ed)
          visited(ed) = true
          res += value
        }
      }

      res
    }
  }

  def solution(n: Int, s: Int, edges: List[(Int, Int, Int)]): Int = {
    val g = new Graph(n)
    edges foreach { case (a, b, v) => g.addUnDirEdge(a, b, v) }
    g.prim(s)
  }

  def main(args: Array[String]): Unit = {
    val Array(n, m) = readLine split " " map { _.toInt }
    val edges: List[(Int, Int, Int)] = ((1 to m) map { _ => readLine split " " map { _.toInt } match {
      case Array(a, b, c) => (a - 1, b - 1, c)
      case _ => sys.error("Bad input")
    }}).toList
    val s = readInt() - 1
    println(solution(n, s, edges))
  }
}
