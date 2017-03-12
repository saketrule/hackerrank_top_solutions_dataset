object Solution {

  val MOD = 1000000007

  def main(args: Array[String]) {
    for (i <- 1 to readLine.toInt) {
      readLine

      var yCuts = readLine.split(" ").map(_.toLong).sortWith(_ > _)
      var xCuts = readLine.split(" ").map(_.toLong).sortWith(_ > _)

      var x = 0
      var y = 0
      var res: Long = 0

      while (x < xCuts.size && y < yCuts.size) {
        if (xCuts(x) > yCuts(y)) {
          res = (res + ((xCuts(x) * (y + 1)) % MOD) % MOD)
          x += 1
        } else {
          res = (res + ((yCuts(y) * (x + 1)) % MOD) % MOD)
          y += 1
        }
      }

      while (x < xCuts.size) {
        res = (res + ((xCuts(x) * (y + 1)) % MOD) % MOD)
        x += 1
      }

      while (y < yCuts.size) {
        res = (res + ((yCuts(y) * (x + 1)) % MOD) % MOD)
        y += 1
      }

      System.out.println(res % MOD)
    }

  }

}