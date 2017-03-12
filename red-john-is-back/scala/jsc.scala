object Solution {
  val placementCounts = new Array[Int](41)
  for (i <- 0 to 3) placementCounts(i) = 1
  for (i <- 4 to 40) placementCounts(i) = placementCounts(i - 1) + placementCounts(i - 4)

  val primeCounts = {
    val n = 217287
    val m = (n - 1)/2
    val isPrime = Array.fill[Boolean](m)(true)
    var i = 0
    var p = 3
    val sqrtN = math.sqrt(n).toInt

    while (p <= sqrtN) {
      if (isPrime(i)) {
        var j = (p*p - 3) >> 1
        while (j < m) {
          isPrime(j) = false
          j += p
        }
      }
      i += 1; p += 2
    }

    val res = new Array[Int](n)
    var curCount = 1
    res(2) = curCount

    i = 0
    p = 3
    while (i < (n - 3) / 2) {
      if (isPrime(i)) curCount += 1
      res(p) = curCount
      res(p + 1) = curCount
      i += 1; p += 2
    }
    res
  }

  def main(args: Array[String]): Unit = {
    for (_ <- 0 until readInt(); n = readInt()) {
      println(primeCounts(placementCounts(n)))
    }
  }
}
