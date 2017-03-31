import java.io.{FileInputStream, InputStreamReader, BufferedReader}

import scala.collection.immutable

/**
 * @author starasov
 */
object SherlockAndMiniMax {
  val debug = false

  def println(s: AnyRef) {
    if (debug) {
      System.out.println(s)
    }
  }

  def println() {
    if (debug) {
      System.out.println()
    }
  }

  def binaryMinSearchRecur(n: Long, input: IndexedSeq[Long], left: Int, right: Int): Int = {
    if (right == left) {
      return left
    }

    //    print(input.slice(left, right))

    val mid = left + (right - left) / 2

    val leftDiff = Math.abs(n - input(left))
    val midDiff = Math.abs(n - input(mid))

    if (midDiff == 0) {
      mid
    } else if (leftDiff > midDiff) {
      binaryMinSearchRecur(n, input, mid, right)
    } else {
      binaryMinSearchRecur(n, input, left, mid)
    }
  }

  def binaryMinSearch(n: Long, input: IndexedSeq[Long]): Int = {
    binaryMinSearchRecur(n, input, 0, input.size)
  }

  def findMin(n: Long, range: Seq[Int]): Long = {
    0
  }

  def findMiniMax(input: IndexedSeq[Long], nIndex: Int, p: Int, q: Int): List[(Long, Long)] = {
    val currentValue = input(nIndex)

    println(s"currentValue: $currentValue")

    if (currentValue < p) {
      return List((p.toLong, Math.abs(p - currentValue)))
    } else if (currentValue > q) {
      return List((q.toLong, Math.abs(currentValue - q)))
    }

    val leftValue = if (nIndex > 0 && input(nIndex - 1) > p) {
      input(nIndex - 1)
    } else {
      p
    }
    val rightValue = if (nIndex < (input.size - 1) && input(nIndex + 1) < q) {
      input(nIndex + 1)
    } else {
      q
    }

    val leftMedian = leftValue + (currentValue - leftValue) / 2
    val leftMedian1 = leftValue + (currentValue - leftValue) / 2 + 1
    val rightMedian = currentValue + (rightValue - currentValue) / 2
    val rightMedian1 = currentValue + (rightValue - currentValue) / 2 + 1

    println(s"leftValue: $leftValue, rightValue: $rightValue")
    println(s"leftMedian: $leftMedian, rightMedian: $rightMedian")
    println(s"leftMedian1: $leftMedian1, rightMedian1: $rightMedian1")
    println()

    val leftList = if (leftValue <= p || leftValue >= q) {
      List((leftValue, Math.abs(currentValue - leftValue)))
    } else {
      List()
    }
    val rightList = if (rightValue <= p || rightValue >= q) {
      List((rightValue, Math.abs(currentValue - rightValue)))
    } else {
      List()
    }

    leftList ++ rightList ++ List((leftMedian, Math.abs(currentValue - leftMedian)), (rightMedian, Math.abs(currentValue - rightMedian)))
  }

  def findMiniMax2(input: IndexedSeq[Long], nIndex: Int, p: Int, q: Int): Seq[(Long, Long)] = {
    val currentValue = input(nIndex)

    val leftValue = selectLeftValue(input, nIndex, p)
    val rightValue = selectRightValue(input, nIndex, q)

    println(s"currentValue: $currentValue, leftValue: $leftValue, rightValue: $rightValue")

    val leftMedian = leftValue + (currentValue - leftValue) / 2
    val leftMedianCorrected = if (leftMedian < p) { p } else if (leftMedian > q) { q } else { leftMedian }

    val rightMedian = currentValue + (rightValue - currentValue) / 2
    val rightMedianCorrected = if (rightMedian < p) { p } else if (rightMedian > q) { q } else { rightMedian }

    println(s"leftMedian: $leftMedian, rightMedian: $rightMedian")
    println(s"leftMedianCorrected: $leftMedianCorrected, rightMedianCorrected: $rightMedianCorrected")

    val leftList = if (leftValue == p && nIndex == 0) { List(leftValue) } else { List() }
    val righList = if (rightValue == q && nIndex == input.size - 1) { List(rightValue) } else { List() }

    (leftList ++ righList ++ List(leftMedianCorrected, rightMedianCorrected)).map((l) => (l, Math.abs(currentValue - l)))
    //
    //
    //    val leftList = if (leftValue <= p || leftValue >= q) { List((leftValue, )) } else { List() }
    //    val rightList = if (rightValue <= p || rightValue >= q) { List((rightValue, Math.abs(currentValue - rightValue))) } else { List() }
    //
    //    leftList ++ rightList ++ List((leftMedian, Math.abs(currentValue - leftMedian)),(rightMedian, Math.abs(currentValue - rightMedian)))
  }

  def selectLeftValue(input: IndexedSeq[Long], nIndex: Int, p: Int): Long = {
    val selectedValue = input(nIndex)
    if (nIndex > 0) { input(nIndex - 1) } else if (selectedValue > p) { p } else { selectedValue }
  }

  def selectRightValue(input: IndexedSeq[Long], nIndex: Int, q: Int): Long = {
    val selectedValue = input(nIndex)
    if (nIndex < (input.size - 1)) { input(nIndex + 1) } else if (selectedValue < q) { q } else { selectedValue }
  }

  def findMin(input: IndexedSeq[Long], p: Int, q: Int): Long = {
    val sortedInput = input.sorted
    if (debug) { println(sortedInput) }

    val r = (0 until sortedInput.size).flatMap((i) => {
      findMiniMax2(sortedInput, i, p, q)
    })

    println(r)

    //    val r = input.sorted.map((n) => {
    //      findMiniMax(input, )
    //      (p to q).map((m) => (m, n, Math.abs(n - m))).min(Ordering.by((p: (Int, Long, Long)) => p._3))
    //    })

    //    val r = input.sorted.map((n) => {
    //      (p to q).map((m) => (m, n, Math.abs(n - m)))
    //    })

//    val r1 = (p to q).map((m) => {
//      input.map((n) => (m, Math.abs(n - m))).min(Ordering.by((p: (Int, Long)) => p._2))
//    })
//    println(r1)

    //    val sorted = input.sorted
    //
    //    val r = range.map((m) => {
    //      val index = binaryMinSearch(m, sorted)
    //      (m, Math.abs(m - sorted(index)))
    //    })


    val by = r.groupBy(_._1)
    val mins = by.values.map((v) => v.min(Ordering.by((p: (Long, Long)) => p._2))).toList
    val sortedMins = mins.sortBy(_._1)

    println(sortedMins)

    sortedMins.maxBy((p) => p._2)._1
  }
}

object Solution extends App {
        val is = System.in
//  val is = new FileInputStream("in/sherlock-and-minimax-3.txt")
  val reader = new BufferedReader(new InputStreamReader(is))
  val entriesCount = reader.readLine().toInt
  val entries = reader.readLine().split(" ").map(_.toLong)
  val range = reader.readLine().split(" ").map(_.toInt)

  println(SherlockAndMiniMax.findMin(entries, range(0), range(1)))
}