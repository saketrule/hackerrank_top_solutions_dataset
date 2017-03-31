import java.io.InputStream
import java.util.Scanner
import scala.collection.immutable.NumericRange
    

object Solution {

    def main(args: Array[String]) {
        println(result(System.in))
    }
   def result(inputStream: InputStream): String = {
    val scanner = new Scanner(inputStream)
    val tescases = scanner.nextLong()
    Range(0, tescases.toInt).map { _ =>
      val samples = scanner.nextLong()
      val numbers = NumericRange(0.toLong, samples, 1).map { _ => scanner.nextLong() }
      val vector = numbers.scan(0L) {
        _ + _
      }.tail.toVector
      play(vector)
    }.mkString("\n")
  }

  def play(vector: Vector[Long]): Int = {
    def rec(off: Long, vec: Vector[Long], acc: Int): Int = {
//      println(s"off: $off, vec: $vec, acc: $acc")
      val partition = (vec.last - off) / 2
      val i = vec.lastIndexOf(partition + off) + 1
      if (i == 0 || vec.length < 2 || vec.last % 2 != 0) {
//        println("bottom")
        acc
      }
      else {
        val first = vec.take(i)
        val rest = vec.drop(i)
//        println(i)
        val left = rec(off, first, acc + 1)
        val right = rec(partition + off, rest, acc + 1)
//        println(s"left: $left, right: $right")
        left max right
      }
    }
    if (vector.head == vector.last) {
      vector.length - 1
    } else rec(0, vector, 0)
  }

}