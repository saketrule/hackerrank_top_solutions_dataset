class UnionFind (n: Int) {
  private val par = Array.range(0, n)
  private val rank = Array.fill(n)(0)

  def root(v: Int): Int =
    if (par(v) == v) v
    else {
      val result = root(par(v))
      par(v) = result
      result
    }

  def unite(u: Int, v: Int): Unit = {
    val x = root(u)
    val y = root(v)
    rank(x) compare rank(y) match {
      case -1 => par(x) = y
      case 1 => par(y) = x
      case 0 => {
        par(x) = y
        rank(y) = rank(y) + 1
      }
    }
  }

  def same(u: Int, v: Int): Boolean =
    root(u) == root(v)
}

case class Edge (val cost: Int, val u: Int, val v: Int) extends Ordered[Edge] {
  def compare (that: Edge): Int =
    if (cost != that.cost) cost compare that.cost
    else (cost + u + v) compare (that.cost + that.u + that.v)
}

object Solution {
  def kruskal(n: Int, es: Array[Edge]): Int = {
    scala.util.Sorting.quickSort(es)
    val uf = new UnionFind(n)
    es.foldLeft(0) { case (acc, Edge(cost, u, v)) =>
      if (uf.same(u, v)) acc
      else {
        uf.unite(u, v)
        acc + cost
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val n, m = sc.nextInt
    val es = Array.fill(m) {
      val x, y, r = sc.nextInt
      Edge(r, x - 1, y - 1)
    }
    val s = sc.nextInt
    println(kruskal(n, es))
  }
}
