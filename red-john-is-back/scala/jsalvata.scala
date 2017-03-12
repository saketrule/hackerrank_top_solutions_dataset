import scala.io.StdIn._
import Math.min
import scala.collection.SortedMap
import scala.util.Random
import scala.collection.mutable.WrappedArray

object Solution {
  def run = multi {
    val n = readInt

    println(solve(n))
  }

  val maxM= 217286
  val sqrtMaxM= 466

  def solve(n: Int): Int = {
    val m=
      (for (
          b4 <- 0 to n / 4;
          val numerator= 2 to n-b4*3;
          val denominator= (2 to b4) ++ (2 to n-b4*4)
        )
        yield prodDiv(numerator, denominator)
      ).sum
    val p= Array.ofDim[Boolean](maxM+1)
    for (i <- 1 to maxM) p(i)= true
    for (i <- 2 to sqrtMaxM) {
      for (j <- 2*i to maxM by i) p(j)= false
    }
    (2 to m).count(i => p(i))
  }

  def test= {
    assert(solve(1)==0)
    assert(solve(7)==3)
    assert(solve(40) > 1000)
    println("OK")
  }

  def prodDiv(numerator: Seq[Int], denominator: Seq[Int]): Int = {
    var acc= 1
    var num= numerator
    var den= denominator
    while (num.nonEmpty || den.nonEmpty) {
      if (num.nonEmpty) {
        acc= (acc * num.head)
        num= num.tail
      }
      while (den.nonEmpty && acc % den.head == 0) {
        acc /= den.head
        den= den.tail
      }
    }
    acc
  }

  /** What follows is stuff that I've found useful when working on HackerRank challenges...
   *  Ideally I would delete any which I don't need, but since time is limited I've deemed it
   *  acceptable to leave them here. 
   */

  def counts[A <% Int](from:A, to: A, s: Seq[A]): Array[Int]= {
    val c= Array.ofDim[Int](to-from+1)
    for (i <- s) c(i-from)+=1
    c
  }

  def counts(l: Array[Int]): List[(Int, Int)] = {
    l.foldLeft(SortedMap.empty[Int, Int]) { (map, n) =>
      map + ((n, 1+map.getOrElse(n, 0)))
    }.toList
  }

  def readInts = readLine.trim.split(' ').toSeq.map(_.toInt)

  val Testing= sys.env.get("LOGNAME") == Some("jordi") // ad-hoc...

  def main(args: Array[String]): Unit = {    
    if (Testing) test else run
  }

  def multi(run: => Unit) {
    val n= readLine.trim.toInt
    for (_ <- 1 to n) run
  }

  def testFile(name: String) {
    val actualName= "test/actual-"+name+".txt"

    print("Running test "+name+"...")
    val newOut= new java.io.PrintStream(actualName)
    val newIn= new java.io.FileInputStream("test/input-"+name+".txt")

    try {
      Console.withIn(newIn)(Console.withOut(newOut)(run))
    } finally {
      newOut.close()
      newIn.close()
    }

    val actual  = io.Source.fromFile(actualName)
    val expected= io.Source.fromFile("test/output-"+name+".txt")
    assert(actual.sameElements(expected))
    println(" passed.")
  }
}
