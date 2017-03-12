object Solution {

 def main(args:Array[String])
  {
    val n = 40
    val res = fillRes(n)
    val primeNum = fillNumPrimes(res)
    val T = readInt
    //println(T)
    for ( k <- 0 to T-1) println(primeNum(readLine.trim.toInt))
    //for(i <- scala.io.Source.stdin.getLines ) println(i)
  // fill the dynamic programming array O(n) = O(n-1) + O(n-4)
  }
  def fillRes(n:Int):Array[Int] =
  {
    val res = Array.fill(n+1)(0)
    // initial values
    for (i <- 0 until 4) res(i) = 1
    // fill the array
    for (i <- 4 to n) res(i) = res(i-1) + res(i-4)
    res
  }
  // fill the array containing the number of primes for each test case
  def fillNumPrimes(res:Array[Int]):Array[Int] =
  {
    val numPrimes = Array.fill(res.length)(0)
    numPrimes(0) = rangePrimes(0,res(0))
    for (i <- 1 until res.length) numPrimes(i) = numPrimes(i-1) + rangePrimes(res(i-1),res(i))
    numPrimes
  }
  def rangePrimes(start:Int,end:Int):Int =
  {
    (start+1 to end).toList.filter(isPrime(_)).filter(_>1).length
  }
  def isPrime(start:Int):Boolean = 
  {
     val s = Math.sqrt(start)
     Stream.from(2).takeWhile(a => (a <= s)) forall (start % _ != 0)
  }
}