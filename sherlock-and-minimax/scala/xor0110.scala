object Solution {

    def main(args: Array[String]) {
        val input = io.Source.stdin.getLines
        val n = input.next().toInt
        val a = input.next().split(" ").take(n).map(_.toInt).toVector.sorted
        val Array(p,q) = input.next().split(" ").take(2).map(_.toInt)
        val rr = (a zip a.tail) map { case (x, y) =>
            ( (y - x) / 2, (x + y) / 2) } filter { case (d, x) => 
            x >= p && x <= q
            } 
        val rra = rr.sorted.takeRight(1)
        val rrp = (a map (x => (math.abs(x - p), p))).sorted.take(1)
        val rrq = (a map (x => (math.abs(x - q), q))).sorted.take(1)
        val qq = (rra ++ rrp ++ rrq).sorted
        val result = (qq filter { case (d, x) => d == qq.last._1 }).head._2
        // println(qq)
        println(result)
    }
}