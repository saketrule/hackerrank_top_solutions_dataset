object Solution {

def main(args: Array[String]) {
    val T = Console.readLine().toInt
    for (i <- 1 to T) {
      val N = Console.readLine().toInt
      println(solve(N))
    }
  }

  def solve(N: Int): Int = {
    val arr = new Array[Int](4 + N)
    arr(0) = 1
    arr(1) = 1
    arr(2) = 1
    arr(3) = 2
    for (i <- 4 to (N - 1)) {
      arr(i) = arr(i - 1) + arr(i - 4)
    }
    sieveOfEratosthenes(arr(N - 1)).size
  }

  def sieveOfEratosthenes(limit: Int) = {
    val (primes: scala.collection.parallel.mutable.ParSet[Int], sqrtLimit) = (scala.collection.parallel.mutable.ParSet.empty ++ (2 to limit), math.sqrt(limit).toInt)
    def prim(candidate: Int): Unit = {
      if (candidate <= sqrtLimit) {
        if (primes contains candidate) primes --= candidate * candidate to limit by candidate
        prim(candidate + 1)
      }
    }
    prim(2)
    primes
  }
}