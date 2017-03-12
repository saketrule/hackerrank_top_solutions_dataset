object Solution {

    def main(args: Array[String]) {
      val n = readLine.trim.toInt

      if (n < 1 || n > 1000) println("Invalid value for N")
      else {
        val xs = readLine.trim.split(" ").map(_.trim.toInt).toList

        if (xs.length != n) println("Invalid number of items")
        else {
          val ls = cutTheSticks(xs, Nil)
          ls.foreach(println)
        }
      }
    }
  
  def cutTheSticks(xs: List[Int], ls: List[Int]): List[Int] = xs match {
    case Nil => ls.reverse
    case h :: t => 
      val cutLen = xs.min
      val xs1 = xs.map(_ - cutLen)
      cutTheSticks(xs1.filter(_ > 0), xs.length :: ls)
  }
}