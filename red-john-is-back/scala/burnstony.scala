object Solution {
  def factorial(n:BigInt):BigInt = if (n==0) 1 else ((n) * factorial((n-1)))
  def smallerPrimes(max:BigInt):Int = {
    primes.takeWhile(_ < max+1).toList.length
  }
  lazy val primes: Stream[Int] = 2 #:: Stream.from(3).filter(i => primes.takeWhile{j => j * j <= i}.forall{ k => i % k > 0})

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines()
    val firstLine = input.next()

    while (input.hasNext) {
      val N = input.next.toInt
      //println("N:" + N.toString)
      var combinations:BigInt = 0
      for (fours <- 0 to N / 4) {
        val ones = N - (fours * 4)
        combinations += factorial(BigInt(fours + ones)) / (factorial(BigInt(fours)) * factorial(BigInt(ones)))
      }
      val p = smallerPrimes(combinations)
      //println("combinations:"+combinations.toString())
      println(p)
    }
  }
}
