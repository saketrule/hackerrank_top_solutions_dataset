import java.util.Scanner

case class SetNode(var parent: Int, var hasEnemy: Boolean)

object Solution {
  val mod: Int = 1000000007

  def maxScore(a:Vector[Long]):Int = {
    val sum = a.sum

    if (sum % 2 == 1) {
      return 0
    }

    var partialSum = 0l
    var splitIndex = 0

    while (splitIndex < a.length && partialSum != sum / 2) {
      partialSum += a(splitIndex)
      splitIndex += 1
    }

    if (splitIndex == a.length) {
      0
    } else {
      val (a1, a2) = a.splitAt(splitIndex)
      math.max(maxScore(a1), maxScore(a2)) + 1
    }
  }
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val t = sc.nextInt()
    for (_ <- 1 to t) {
      val n = sc.nextInt()
      val a = (1 to n).map( _ => sc.nextLong()).toVector
      if (a.forall(_ == 0l)) {
        println(a.length - 1)
      } else {
        println(maxScore(a))
      }
    }
  }
}
