import java.util.Scanner
import java.io._
object Solution {

    def main(args: Array[String]) {
        val in = new Scanner(System.in)
        val n = in.nextInt
        var sticks = Seq.fill(n)(in.nextInt)
        while(sticks.nonEmpty) {
           println(sticks.size)
           sticks = sticks.map{_ - sticks.min}.filter(_ != 0)
        }
    }
}