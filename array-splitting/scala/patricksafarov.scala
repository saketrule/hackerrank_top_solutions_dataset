import scala.util.control.Breaks._
object Solution extends App {
    for (List(List(n), arr) <- io.Source.stdin.getLines.toList.tail.map(_.split(' ').map(_.toLong).toList).grouped(2)) {
        println(getMaxDepth(arr))
    }
    
    def getMaxDepth(arr: List[Long]): Int = {
        if (arr.size < 2) {
            0
        } else {
            val sums = arr.scan(0L)(_ + _).tail
            val splitIndex = sums.indexOf(sums.last / 2.0)
            if (splitIndex >= 0) {
                val (arr1, arr2) = arr.splitAt(splitIndex + 1)
                Math.max(getMaxDepth(arr1), getMaxDepth(arr2)) + 1
            } else {
                0
            }
        }
    }
}