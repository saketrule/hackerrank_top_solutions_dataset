import java.util.Scanner

import scala.collection.mutable

object Solution {
  val mod: Int = 1000000007

  def func(arr:Seq[Int], m: Int): Int =
    arr.map(i => math.abs(i - m)).min

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val arr = (1 to n).map(_ => sc.nextInt()).sorted
    val p = sc.nextInt()
    val q = sc.nextInt()
    val candidates = p :: q :: arr.tail.foldLeft((arr.head, List[Int]())) {
      case ((prev, soFar), curr) if  (curr + prev) % 2 == 0 => (curr, ((curr + prev ) / 2) :: soFar)
      case ((prev, soFar), curr)  => (curr, ((curr + prev - 1) / 2) :: soFar)
    }._2
    
    println(candidates.filter(i => i >= p && i <= q).sorted.map(i => (i,func(arr, i))).maxBy(_._2)._1)
  }

}

