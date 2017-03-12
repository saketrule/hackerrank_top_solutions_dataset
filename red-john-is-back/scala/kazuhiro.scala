object Solution {

    def main(args: Array[String]) {
          val lines = io.Source.stdin.getLines().toList.map(_.toInt)
  val (cases, columns) = (lines.head, lines.tail)

  val c = columns.head

  def layBricks(n: Int, i: Int): Int = {
    if (n == i) 1
    else if (n > i) 0
    else layBricks(n + 1, i) + layBricks(n + 4, i)
  }

  def sieve(n: Int) = (2 to math.sqrt(n).toInt).foldLeft((2 to n).toSet) { (ps, x) =>
    if (ps(x)) ps -- (x * x to n by x)
    else ps
  }
      
  for (i <- columns) {
    val bricks = layBricks(0, i)
    if (bricks < 2) println(0)
    else println(sieve(bricks).size)    
  }
  }
}