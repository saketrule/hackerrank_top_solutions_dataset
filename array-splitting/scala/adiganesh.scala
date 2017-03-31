object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      lines.next().toInt // Number of elements

      val line = lines.next()
      val values = line.split(" ")
      val data = values.map(_.toInt)
      val total = data.sum

      println(computePoints(0, values.length - 1, data, total))
    }
  }

  def computePoints(startIndex: Int, endIndex: Int, data: Array[Int], total: Int): Int = {
    var points = 0
    var currentIndex = startIndex
    var partialSum = data(currentIndex)
    while(currentIndex < endIndex && points == 0) {
      if (partialSum == total - partialSum) {
        points = 1
        val leftPoints = computePoints(startIndex, currentIndex, data, partialSum)
        val rightPoints = computePoints(currentIndex + 1, endIndex, data, partialSum)
        points += Math.max(leftPoints, rightPoints)
      }
      currentIndex += 1
      partialSum += data(currentIndex)
    }
    points
  }
}