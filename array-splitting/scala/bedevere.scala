object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    for (_ <- 0 until sc.nextInt()) yield {
        
      val n: Int = sc.nextInt()
      val a = List.fill(n)(sc.nextLong()) filter (_ != 0)
          
      if (a.isEmpty) println(n - 1)
      else {
        val partialSum = a.tail.scan(a.head)(_ + _)
            
        @scala.annotation.tailrec
        def loop(a: List[List[Long]] = List(partialSum.dropRight(1)), sum: Long = partialSum.last, acc: Int = 0): Int = {
          if (sum % 2 == 1) acc
          else {
            val nextSum = sum / 2
            val cut = a flatMap { b =>
              val (left, right) = b span (_ < nextSum)
              if (right.headOption.fold(false)(_  == nextSum)) List(left, right.tail map (_ % nextSum))
              else Nil
            }
            if (cut.isEmpty) acc
            else loop(cut filter (_.nonEmpty), nextSum, acc + 1)
          }
        }
          
        println(loop())
      }
    }
  }
}