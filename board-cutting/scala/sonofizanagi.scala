object Solution {

  trait Direction {
    def isVertical(): Boolean
    def value(): BigInt
  }

  case class Vertical(value: BigInt) extends Direction {
    def isVertical(): Boolean = true
  }

  case class Horizontal(value: BigInt) extends Direction {
    def isVertical(): Boolean = false
  }

  def minimizeCutting(xs: List[BigInt], ys: List[BigInt]): BigInt = {
    def loop(ds: List[Direction], v: Int, h: Int, cost: BigInt): BigInt = {
      if (ds.isEmpty) cost
      else {
        if (ds.head.isVertical) loop(ds.tail, v, h + 1, cost + ((v + 1) * ds.head.value))
        else loop(ds.tail, v + 1, h, cost + ((h + 1) * ds.head.value))
      }
    }
    
    val ds = (xs.map(Vertical(_)) ::: ys.map(Horizontal(_))).sortWith(_.value > _.value)
    loop(ds, 0, 0, BigInt(0))
  }

  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines().toList
    lines.tail.grouped(3).foreach(ls => printResult(ls))
  }

  def printResult(ls: List[String]): Unit = {
    val xs = ls.tail.head.split(" ").map(BigInt(_)).toList
    val ys = ls.tail.tail.head.split(" ").map(BigInt(_)).toList
    val result = minimizeCutting(0 :: xs, 0 :: ys)
    println(result % 1000000007)
  }
}