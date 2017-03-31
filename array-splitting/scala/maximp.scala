import scala.collection.mutable
object Solution {

     def getSplits(start: Long, stop: Long, dividers: mutable.HashSet[Long], minSetSum: Long) :Int = {
    if ((stop - start > minSetSum) && (dividers.contains(((start + stop)/2) / minSetSum))) {
      1 + math.max(getSplits(start, (start + stop)/2, dividers, minSetSum), getSplits((start + stop)/2, stop, dividers, minSetSum) )
    } else 0
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    //val sc = new java.util.Scanner(new File("/home/maxim/hackerrank/nikita2.txt"))
    for (t <- 0 until sc.nextInt) {
      val n = sc.nextInt
      var arr = new Array[Int](n)
      var totalSum = 0L
      for (i <- 0 until n) {
        arr(i) = sc.nextInt
        totalSum += arr(i)
      }
      if (totalSum == 0) {
        println (n - 1)
      } else {
        var minSetSum = totalSum
        var maxChainSize = 0
        var remaining = totalSum
        while (remaining % 2 == 0) {
          remaining = remaining / 2
          minSetSum = remaining
          maxChainSize += 1
        }
        if (maxChainSize > 0) {
          var currentSum = 0L
          var dividers = mutable.HashSet[Long]()
          for (i <- 0 until n) {
            currentSum += arr(i)
            if (currentSum % minSetSum == 0) {
              dividers += currentSum / minSetSum
            }
          }
          println(getSplits(0L, totalSum, dividers, minSetSum))
        } else println(0)

      }
      }
  }
}