/**
 * Created by dmitryshaporenkov on 27/01/2015.
 */
object Solution {

  def MOD = 1000000007

  def main(args:Array[String]) = {
    val T = readLine.toInt
    for (t <- 1 to T) {
      val s = readLine.split(' ')
      val M = s(0).toString
      val N = s(1).toString

      val y = readLine.split(' ').map (i => i.toLong).sortBy(i => -i)
      val x = readLine.split(' ').map (i => i.toLong).sortBy(i => -i)

      var xcuts = 0
      var ycuts = 0
      var res = 0L
      for (i <- 1 to x.length + y.length) {
        def cutVertical() = {
          val nCutHor = ycuts + 1
          val newRes = ((x(xcuts) * nCutHor.toLong) % MOD + res) % MOD
          res = newRes
          xcuts = xcuts + 1
        }

        def cutHorizontal() = {
          val nCutVert = xcuts + 1
          val newRes = ((y(ycuts) * nCutVert.toLong) % MOD + res) % MOD
          res = newRes
          ycuts = ycuts + 1
        }

        if (ycuts == y.length && xcuts == x.length) res
        else if (xcuts == x.length) {
          cutHorizontal()
        } else if (ycuts == y.length) {
          cutVertical()
        } else {
          if (x(xcuts) > y(ycuts)) cutVertical()
          else cutHorizontal()
        }
      }

      println(res)
    }
  }
}
