import java.util.Scanner

object Solution {
  def solve(xs: Seq[Int]): Int = partition(xs) match {
      case None => 0
      case Some((us, vs)) => 1 + (solve(us) max solve(vs))  
  }  
    
  private def partition(xs: Seq[Int]): Option[(Seq[Int], Seq[Int])] = {
    val s = xs.foldLeft(0L)(_ + _)
    val n = xs.length
    if (s % 2 != 0 || n <= 1) None
    else xs.toStream.scanLeft(0L)(_ + _).zipWithIndex.tail.find(_._1 == s / 2).map(x => xs.splitAt(x._2))
  }
    
    def main(args: Array[String]) {
        val sc = new Scanner(System.in)
        val t = sc.nextInt()
        Seq.fill(t) {
            val n = sc.nextInt()
            val xs = Seq.fill(n)(sc.nextInt()) 
            println(solve(xs))
        }    
    }
}