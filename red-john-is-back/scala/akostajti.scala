import scala.collection.mutable.BitSet

/**
 * https://www.hackerrank.com/challenges/red-john-is-back
 */
object Solution extends App {
  
  def sieve(nTo: Int) =   {
	  val primes = BitSet.empty.par ++ (2 to nTo)
	  for {
	    candidate <- 2 to Math.sqrt(nTo).toInt
	    if primes contains candidate
	  } primes --= candidate * candidate to nTo by candidate
	  primes
  }
  
  def solve(n: Int): Int = {
    if (n < 4) {
      0
    } else {
	    val a = Array.ofDim[Int](n + 1)
	    for (i <- 0 to 3) a(i) = 1
	    
	    for (i <- 4 to n) {
	      a(i) = a(i - 1) + a(i - 4)
	    }
	    sieve(a(n)).size
    }
  }
  
  val t = readInt
  for (i <- 0 until t) {
    val n = readInt
    println(solve(n))
  }
}