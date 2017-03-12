import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}

object Solution {

  def main(args: Array[String]) {
    def readParameters: Array[Int] = { readInt(); readLine().split(' ').map(_.toInt) }

    cutSticks(readParameters, Nil) foreach println
  }

  @tailrec
  private def cutSticks(sticks: Array[Int], acc: List[Int]): List[Int] = {
    if (sticks.isEmpty) acc.reverse
    else cutSticks(sticks map (_ - sticks.min) filter (_ > 0), sticks.size :: acc)
  }
}