object Solution {
    
    def loop(xs: List[Int]): List[Int] = if (xs == Nil) Nil
        else xs.length :: loop(xs.map(_ - xs.min).filter(_ > 0))

    def main(args: Array[String]) {
        loop(io.Source.stdin.getLines.toList(1).split(" ").map(_.toInt).toList).foreach(println)
    }
}