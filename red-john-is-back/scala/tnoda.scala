import java.util.Scanner
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Long.bitCount
import java.nio.CharBuffer
import java.util.Arrays.binarySearch
import scala.annotation.tailrec
import scala.annotation.switch
import scala.annotation.unchecked
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.PriorityQueue
import scala.math.Ordering.Implicits._
import scala.util.Random
import scala.util.control.Breaks._

object Solution extends App {
  val sc = new Scanner(System.in)
  val out = new PrintWriter(System.out)
  
  object Input {
    import java.lang.Character._
    
    lazy val in = new BufferedReader(new InputStreamReader(java.lang.System.in))
    var buf: Array[Char] = Array()
    var pos: Int = 0
    
    def next(): String = {
      while (pos == buf.length) {
        buf = in.readLine.toArray
        pos = 0
      }
      while (isWhitespace(buf(pos))) {
        pos += 1
      }
      while (pos == buf.length) {
        buf = in.readLine.toArray
        pos = 0
      }
      val s = pos
      while (pos < buf.length && !isWhitespace(buf(pos))) {
        pos += 1
      }
      new java.lang.String(buf, s, pos - s)
    }
    
    def nextInt(): Int = next.toInt
    
    def nextLong(): Long = next.toLong
    
    def nextFloat(): Float = next.toFloat
    
    def nextDouble(): Double = next.toDouble
    
    val nextString: () => String = next _
  }
  
  import Input._
  
  def solve(sum: Array[Int]): Int = {
    val N = nextInt
    val dp = Array.ofDim[Int](50)
    dp(0) = 1
    for (i <- 0 until N) {
      dp(i + 1) += dp(i)
      dp(i + 4) += dp(i)
    }
    val M = dp(N)
    sum(M+1)
  }
  
  val primes = Array.fill(1000000)(1)
  primes(0) = 0
  primes(1) = 0
  for (i <- 2 until 1000 if primes(i) == 1)
    for (j <- 2 * i until 1000000 by i)
      primes(j) = 0
  val sum = Array.ofDim[Int](1000001)
  for (i <- 2 until 999999)
    sum(i + 1) = sum(i) + primes(i)
  val T = nextInt
  for (_ <- 0 until T)
    out.println(solve(sum))
  out.flush
}
