import scala.annotation.tailrec

object Solution {
  val mod = 1000000007

  @tailrec
  final def solve(x: List[Long], y:List[Long], xn:Long = 1, yn:Long = 1, acc:Long = 0): Long = (x,y) match {
    case (Nil,Nil) => acc
    case (_,_::_) if x == Nil || x.head <= y.head => solve(y.tail,x,yn+1,xn, (acc + y.head*xn) % mod)
    case (_,_) => solve(y,x,yn,xn,acc)
  }

  def main(args: Array[String]) {
    val t = readLine().toInt
    (1 to t).foreach(_ => {
        val m::n::_ = readLine().split(" ").map( _.toInt ).toList
        val x = readLine().split(" ").map( _.toLong ).sorted.toList.reverse
        val y = readLine().split(" ").map( _.toLong ).sorted.toList.reverse
        println(solve(x,y))
      })
  }
}
