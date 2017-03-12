import annotation.tailrec

object Solution {

    def main(args: Array[String]) {
        val t = readInt()
        for(_ <- 0 until t) {
            val m :: n :: Nil = readLine().split(" ").map(_.toInt).toList
            val yList = readLine().split(" ").map(_.toInt).sorted.reverseIterator.toList
            val xList = readLine().split(" ").map(_.toInt).sorted.reverseIterator.toList
            println(xx(yList, xList))
        }
    }
    
    @tailrec
    def xx(yList: List[Int], xList: List[Int], cost: Long = 0L, ySegment: Int = 1, xSegment: Int = 1): Long = {
        (yList, xList) match {
            case (_ , Nil) =>
                (cost + yList.foldLeft(0L)(_ + _) * xSegment) % 1000000007L
            case (Nil, _) =>
                (cost + xList.foldLeft(0L)(_ + _) * ySegment) % 1000000007L
            case (y :: yTail, x :: xTail) =>
                if (y >= x)
                    xx(yTail, xList, (cost + y.toLong * xSegment) % 1000000007L, ySegment + 1, xSegment)
                else
                    xx(yList, xTail, (cost + x.toLong * ySegment) % 1000000007L, ySegment, xSegment + 1)
        }
    }
}