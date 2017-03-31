import scala.collection.immutable.Queue

object Solution {

  def solution(A: Array[Int], N: Int): Int = {
    // prefix sums from 0 to i
    val prefixSum = new Array[BigInt](N)
    prefixSum(0) = A(0)

    var i = 1
    while(i < N) {
      prefixSum(i) = prefixSum(i - 1) + A(i)
      i += 1
    }

    var maxPoints = 0
    var pendingGames = Queue((0, N - 1, 0))

    while(pendingGames.nonEmpty) {
      var ((li, ri, points), newPendingGames) = pendingGames.dequeue
      pendingGames = newPendingGames

      val l = ri - li + 1
      if(l >= 2 && prefixSum(ri) % 2 == 0) {
        // length is >= 2 and sum is even, so we can divide the array

        // search for subarray with sum == halfSum
        val halfSum = prefixSum(ri) / 2
        i = li
        while(i < ri && prefixSum(i) < halfSum) {
          i += 1
        }
        if(prefixSum(i) == halfSum) {
          // there's a subarray with sum == halfSum
          // we got a point
          points += 1

          // since we're taking the left subarray, prefixSums are ok
          pendingGames = pendingGames.enqueue((li, i, points))

          // since we're taking the right subarray we must subtract halfSum
          // from the prefixSums
          i = i + 1
          pendingGames = pendingGames.enqueue((i, ri, points))
          while(i <= ri) {
            prefixSum(i) -= halfSum
            i += 1
          }

        }
      }

      if(points > maxPoints) {
        maxPoints = points
      }

    }
    maxPoints
  }

  def main(args: Array[String]) {

    val sc = if(args.isEmpty) {
      new java.util.Scanner(System.in)
    } else {
      new java.util.Scanner(args(0))
    }

    val T = sc.nextInt()
    var t = 0
    while(t < T) {
      val N = sc.nextInt()
      val A = new Array[Int](N)
      var i = 0
      while(i < N) {
        A(i) = sc.nextInt()
        i += 1
      }

      val s = Solution.solution(A, N)
      println(s)

      t += 1
    }

  }
}