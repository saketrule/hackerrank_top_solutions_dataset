import java.util.Scanner

/**
 * Created by hama_du on 2014/02/16.
 */
object Solution {
  val MOD = 1000000007

  val in = new Scanner(System.in)

  def solve(): Long = {
    var ans:Long = 0
    val N,M = in.nextInt
    val x = (0 until N-1).map(_ => in.nextLong).toArray.sortWith(_ > _)
    val y = (0 until M-1).map(_ => in.nextLong).toArray.sortWith(_ > _)
    var xi,yi = 0
    while (xi < N-1 || yi < M-1) {
      if (xi < N-1 && yi < M-1) {
        if (x(xi) < y(yi)) {
          ans += (y(yi) * (xi+1))
          ans %= MOD
          yi += 1
        } else {
          ans += (x(xi) * (yi+1))
          ans %= MOD
          xi += 1
        }
      } else if (xi == N-1) {
        ans += (y(yi) * (xi+1))
        ans %= MOD
        yi += 1
      } else {
        ans += (x(xi) * (yi+1))
        ans %= MOD
        xi += 1
      }
    }
    ans
  }

  def main(args: Array[String]) {
    val T = in.nextInt()
    (0 until T).foreach(_ => println(solve()))
  }
}