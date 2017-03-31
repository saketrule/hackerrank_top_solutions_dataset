object Solution {

    def main(args: Array[String]) {
         1.to(readLine().toInt).foreach(caseNum => {
    readLine() // skip line I don't care about
    val game = new GameSolver(readLine().split(" ").map(_.toInt))

    println(game.getBestScore())
  })
    }
}

class GameSolver(array: Array[Int]) {
  val splitFinder = new SplitFinder(array)

  def getBestScore(startIndex: Int = 0, endIndex: Int = array.size-1): Int = {
      splitFinder.getSplit(startIndex, endIndex) match {
        case -1 => 0
        case split =>  1 + Math.max(getBestScore(startIndex, split), getBestScore(split + 1, endIndex))
      }
  }
}

/** Key Insights:
  * Only one split per array will matter (only case of multiple is if there's 0's, and that won't affect scoring at all.
  * Can "reset" sum by subtracting value from left or right node respectively (if exists)
  *
 */

class SplitFinder(array: Array[Int]) {
  val leftSums = sumLeft(array)
  val rightSums = sumLeft(array.reverse).reverse

  def sumLeft(array: Array[Int]): Array[Int] = {
    var runningTotal = 0

    array.map(element => {
      runningTotal = runningTotal + element
      runningTotal
    })
  }

  def getSplit(leftIndex: Int, rightIndex: Int): Int = {
    var leftOffset = 0
    var rightOffset = 0

    if (rightIndex + 1 < array.length) {
      rightOffset = rightSums(rightIndex + 1)
    }

    if (leftIndex - 1 >= 0) {
      leftOffset = leftSums(leftIndex - 1)
    }

    leftIndex.until(rightIndex).foreach(candidateIndex => {
      if (leftSums(candidateIndex) - leftOffset == rightSums(candidateIndex + 1) - rightOffset) {
        return candidateIndex
      }
    })

    -1
  }
}
