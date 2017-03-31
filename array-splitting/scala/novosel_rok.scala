object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val t = sc.nextInt()
    // t cases follow
    for (_ <- 0 until t) {
      val n = sc.nextInt()
      val a = new Array[BigInt](n)
      for (a_i <- 0 until n) {
        a(a_i) = sc.nextBigInteger()
      }

      // Algorithm
      println(split(a, 0))
    }
  }

  def split(a: Array[BigInt], points: Int): Int = {
    if (a.length <= 1) return points

    // Split the array into 2 parts with equal sum
    val scan = a.scanLeft(BigInt(0))(_ + _).tail
    if (scan.last % 2 != 0) points
    else if (scan.last == 0) points + (a.length - 1)
    else {
      val elToFind = scan.last / 2
      val result = scan.zipWithIndex.filter(x => x._1 == elToFind)
      if (result.isEmpty) points
      else {
        val splitIndex = result.head._2
        val resLeft = split(a.slice(0, splitIndex + 1), points + 1)
        val resRight = split(a.slice(splitIndex + 1, a.length), points + 1)
        Math.max(resLeft, resRight)
      }
    }
  }
}