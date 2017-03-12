import scala.annotation.tailrec
import scala.io.StdIn._

object Solution {

  // cache answers
  val configs: IndexedSeq[Int] = for (i <- 1 to 40) yield countConfigs(i)
  val primes: Seq[Int] = eulerSieve(configs.max)

  def main(args: Array[String]) {
    for (i <- 1 to readInt) println(solve(readInt))
  }

  def countConfigs(n: Int) = {
    def recurse(count: Int, size: Int): Int = {
      size match {
        case 0 => count
        case 4 => count + 2
        case x if x > 4 => recurse(count, size - 4) + recurse(count, size - 1)
        case _ => count + 1
      }
    }
    recurse(0, n)
  }

  def eulerSieve(n: Int): Seq[Int] = {
    @tailrec
    def sieve(primes: Vector[Int], remaining: Vector[Int]): Seq[Int] = {
      remaining.headOption match {
        case Some(prime) => sieve(primes :+ prime, remaining.tail.filterNot(_ % prime == 0))
        case None => primes
      }
    }
    if (n < 2)
      Vector[Int]()
    else
      sieve(Vector(2), 3.to(n, 2).toVector)
  }

  def solve(n: Int) = primes.count(_ <= configs(n - 1))

}