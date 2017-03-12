import scala.collection.mutable

object Solution {
    def main(args: Array[String]): Unit = {
        val Array(n, m) = readLine() split(" ") map(_ toInt)
        val adj = mutable.ArrayBuffer.fill(n, 0) { 0 }
        val edge = mutable.ArrayBuffer[Tuple2[Int, Int]]()
        for (_ <- 0 until m) {
            val Array(u, v) = readLine() split(" ") map(_ toInt)
            adj(u - 1) += v - 1
            adj(v - 1) += u - 1
            edge += ((u - 1, v - 1))
        }

        val child = mutable.ArrayBuffer.fill(n, 0) { 0 }
        val parent = mutable.ArrayBuffer.fill(n) { 0 }
        val depth = mutable.ArrayBuffer.fill(n) { 0 }
        val nodes = mutable.ArrayBuffer.fill(n) { 0 }

        def root(u: Int): Unit = {
            if (parent(u) != u) {
                depth(u) = depth(parent(u)) + 1
            }

            nodes(u) = 1
            adj(u).par.foreach { v =>
                if (v != parent(u)) {
                    parent(v) = u
                    child(u) += v
                    root(v)
                    nodes(u) += nodes(v)
                }
            }
        }
        root(0)

        println(edge.par.count({ case (uu, vv) =>
            val (u, v) =
                if (depth(uu) < depth(vv)) {
                    (uu, vv)
                } else {
                    (vv, uu)
                }
            (nodes(0) - nodes(v)) % 2 == 0 && nodes(v) % 2 == 0
            }))
    }
}
