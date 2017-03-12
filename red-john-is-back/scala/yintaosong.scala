object Solution {
  val bricks: Stream[Int] = 1 #:: 1 #:: 1 #:: 2 #:: bricks.zip(bricks.tail.tail.tail).map(t => t._1 + t._2)
  val primes: Stream[Int] = 2 #:: Stream.from(3).filter(n => primes.takeWhile(p => p * p <= n).forall(n % _ > 0))
  def magicNum(N: Int): Int = {
    val M = bricks(N - 1)
    val P = primes.takeWhile(p => p <= M)
    P.length
  }
  def main(args: Array[String]): Unit = {
    val T = io.StdIn.readInt()
    (1 to T).map(_ => println(magicNum(io.StdIn.readInt())))
  }
}